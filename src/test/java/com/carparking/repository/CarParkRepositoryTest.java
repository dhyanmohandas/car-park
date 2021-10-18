package com.carparking.repository;

import com.carparking.model.ParkingSlot;
import com.carparking.model.ParkingSlotRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@RunWith(MockitoJUnitRunner.class)
public class CarParkRepositoryTest {

    private CarParkRepository carParkRepository;

    @Before
    public void setUp(){
        carParkRepository = new CarParkRepository();
    }

    @Test
    public void should_createParkingSlot_when_givenValidParkingSlot(){
        ParkingSlotRequest parkingSlotRequest  = new ParkingSlotRequest("KA",2);
        ParkingSlot parkingSlot = carParkRepository.createParkingSlot(parkingSlotRequest);
        assertThat(parkingSlot).isNotNull();
        assertThat(parkingSlot.getId()).isGreaterThan(1);
    }

    @Test
    public void should_validateParkingSlotId_when_givenParkingId(){
        int parkingSlotId = 1;
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KL",2);
        ParkingSlot parkingSlot = carParkRepository.createParkingSlot(parkingSlotRequest);
        boolean parkingIdStatus = carParkRepository.validateParkingSlotId(parkingSlotId);
        assertThat(parkingIdStatus).isTrue();
    }

    @Test
    public void Should_UpdateParkingSlot_When_GivenParkingIdIsValid(){
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KA", 2);
        ParkingSlotRequest parkingSlotRequestExisting = new ParkingSlotRequest("KA", 4);
        ParkingSlot existingParkingSlot = carParkRepository.createParkingSlot(parkingSlotRequestExisting);
        ParkingSlot parkingSlot = carParkRepository.updateParkingSlot(1,parkingSlotRequest);
        assertThat(parkingSlot.getDurationInHours()).isEqualTo(parkingSlotRequest.getDurationInHours());
    }

    @Test
    public void Should_DeleteParkingSlot_When_givenParkingId(){
        int id=1;
        ParkingSlotRequest parkingSlot = new ParkingSlotRequest("KS",1);
        carParkRepository.createParkingSlot(parkingSlot);
        carParkRepository.deleteParkingSlot(id);
        assertThat(carParkRepository.validateParkingSlotId(id)).isFalse();
    }


}
