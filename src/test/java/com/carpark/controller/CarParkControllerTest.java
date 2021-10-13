package com.carpark.controller;

import com.carpark.exception.InvalidDurationException;
import com.carpark.exception.NoParkingSpaceException;
import com.carpark.exception.ParkingSlotNotFound;
import com.carpark.model.ParkingSlot;
import com.carpark.model.ParkingSlotRequest;
import com.carpark.repository.CarParkRepository;
import com.carpark.service.CarParkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CarParkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarParkService carParkService;


    @org.junit.jupiter.api.Test
    public void should_createParkingSlot_when_parkingSlotIsValid() throws Exception{
        given(carParkService.createParkingSlot(any())).willReturn(new ParkingSlot(1,"KA-01", 2, Timestamp.from(Instant.now())));
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA-01",2);
        mockMvc.perform(MockMvcRequestBuilders.post("/parkingSlots").
                contentType(MediaType.APPLICATION_JSON).
                content(new ObjectMapper().writeValueAsString(parkingSlotRequest))).andExpect(status().isOk()).
                andExpect(jsonPath("vehicleNumber").value("KA-01"));
    }

    @Test
    public void should_throwInvalidDuration_when_DurationLessThanOneHour() throws Exception{
        given(carParkService.createParkingSlot(any())).willThrow(new InvalidDurationException());
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA-01",6);
        mockMvc.perform(MockMvcRequestBuilders.post("/parkingSlots").
                contentType(MediaType.APPLICATION_JSON).
                content(new ObjectMapper().writeValueAsString(parkingSlotRequest))).andExpect(status().isBadRequest());
    }

    @Test
    public void should_throwParkingFull_when_parkingIsFull() throws Exception {
        given(carParkService.createParkingSlot(any())).willThrow(new NoParkingSpaceException());
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA-01",6);
        mockMvc.perform(MockMvcRequestBuilders.post("/parkingSlots").
                contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(parkingSlotRequest))).andExpect(status().isNotFound());

    }


    @Test
    public void should_throwParkingSlotNotFound_when_givenInvalidSlot() throws Exception {
        given(carParkService.updateParkingSlot(anyInt(),any())).willThrow(new ParkingSlotNotFound("Parking Slot not found"));
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA",2);
        String objectString = new ObjectMapper().writeValueAsString(parkingSlotRequest);
        mockMvc.perform(MockMvcRequestBuilders.put("/parkingSlots/"+ 12).
                contentType(MediaType.APPLICATION_JSON).content(objectString)).andExpect(status().isNotFound());
    }

    @Test
    public void should_UpdateParkingSlot_when_givenValidSlot() throws Exception {
        given(carParkService.updateParkingSlot(anyInt(),any())).willReturn(new ParkingSlot(2,"KA",2, Timestamp.from(Instant.now())));
        ParkingSlotRequest parkingSlotRequest=new ParkingSlotRequest("KA",3);
        mockMvc.perform(MockMvcRequestBuilders.put("/parkingSlots/"+2).contentType(MediaType.APPLICATION_JSON).
                content(new ObjectMapper().writeValueAsString(parkingSlotRequest))).andExpect(status().isOk());
    }

    @Test
    public void Should_DeleteParkingSlot_ForValidId() throws Exception {
        int is=0;
        mockMvc.perform(MockMvcRequestBuilders.delete("/parkingSlots/" + 2)).andExpect(status().isNoContent());
    }

    @Test
    public void Should_ThrowParkingSlotNotValid_When_GivenInavlidId() throws Exception {
        given(carParkService.deleteParkingSlot(anyInt())).willThrow(new ParkingSlotNotFound("ParkingSlot Not Found"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/parkingSlots/"+22)).andExpect(status().isNotFound());
    }


}
