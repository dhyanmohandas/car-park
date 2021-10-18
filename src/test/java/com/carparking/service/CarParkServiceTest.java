package com.carparking.service;

import com.carparking.exception.InvalidDurationException;
import com.carparking.exception.NoParkingSpaceException;
import com.carparking.exception.ParkingSlotNotFound;
import com.carparking.model.ParkingSlot;
import com.carparking.model.ParkingSlotRequest;
import com.carparking.repository.CarParkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarParkServiceTest {

    private CarParkService carParkService;

    @Mock
    private CarParkRepository carParkRepository;

    @Before
    public void setUp(){
        carParkService = new CarParkService(carParkRepository);
    }


    @Test
    public void should_throwInvalidDurationException_when_durationLessThanOneHour(){
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA",-1);
        Exception InvalidDurationException = assertThrows(InvalidDurationException.class, ()-> carParkService.createParkingSlot(parkingSlotRequest));
    }


    @Test
    public void should_createParkingSlot_when_parkingSlotIsValid(){
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA",2);
        ParkingSlot parkingSlotExpected = new ParkingSlot(1,"KA",2, Timestamp.from(Instant.now()));
        given(carParkRepository.createParkingSlot(any())).willReturn(parkingSlotExpected);
        ParkingSlot parkingSlot = carParkService.createParkingSlot(parkingSlotRequest);
        assertThat(parkingSlotExpected.getVehicleNumber()).isEqualTo(parkingSlot.getVehicleNumber());
    }

    @Test
    public void should_throwNoParkingSpace_when_parkingIsFull(){
        willReturn(10).given(carParkRepository).getParkingSlotAllocated();
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KS",3);
        Exception noParkingSpaceException =  assertThrows(NoParkingSpaceException.class,
                ()->carParkService.createParkingSlot(parkingSlotRequest));
    }


    @Test
    public void should_throwParkingSlotNotFound_when_givenInvalidSlot(){
        int parkingSlotId = 12;
        given(carParkRepository.validateParkingSlotId(parkingSlotId)).willReturn(false);
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA",12);
        Exception parkingSlotNotFoundException = assertThrows(ParkingSlotNotFound.class, ()-> carParkService.updateParkingSlot(12,parkingSlotRequest));
    }

    @Test
    public void should_updateParkingSlot_when_givenValidSlot(){
        ParkingSlot parkingSlotExpected = new ParkingSlot(2,"KA",2,Timestamp.from(Instant.now()));
        given(carParkRepository.validateParkingSlotId(2)).willReturn(true);
        given(carParkRepository.updateParkingSlot(anyInt(),any())).willReturn(parkingSlotExpected);

        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA", 3);
        ParkingSlot parkingSlot = carParkService.updateParkingSlot(2,parkingSlotRequest);
        assertThat(parkingSlotExpected.getId()).isEqualTo(parkingSlot.getId());
        assertThat(parkingSlotExpected.getStartTime()).isEqualTo(parkingSlot.getStartTime());
    }


    @Test
    public void Should_ThrowParkingSlotNotFound_When_IdIsInvalid(){
        given(carParkRepository.validateParkingSlotId(anyInt())).willReturn(false);
        Exception parkingSlotNotFound = assertThrows(ParkingSlotNotFound.class, ()->carParkService.deleteParkingSlot(2));
        assertEquals("Parking Slot not found", parkingSlotNotFound.getMessage());
    }

    @Test
    public void Should_deleteParkingSlot_When_GivenIdIsValid(){
        ParkingSlot parkingSlotExpected = new ParkingSlot(2, "KS", 2,Timestamp.from(Instant.now()));
        given(carParkRepository.validateParkingSlotId(anyInt())).willReturn(true);
        given(carParkRepository.deleteParkingSlot(anyInt())).willReturn(parkingSlotExpected);
        ParkingSlot parkingSlot = carParkService.deleteParkingSlot(2);
        assertEquals(2,parkingSlot.getId());
    }

}
