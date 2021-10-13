package com.carpark.repository;

import com.carpark.model.ParkingSlot;
import com.carpark.model.ParkingSlotRequest;
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
        assertThat(parkingSlot.getId()).isNotNull();
    }

    @Test
    public void should_validateParkingSlotId_when_givenParkingId(){
        int parkingSlotId = 0;
        ParkingSlotRequest parkingSlotRequest = new ParkingSlotRequest("KL",2);
        ParkingSlot parkingSlot = carParkRepository.createParkingSlot(parkingSlotRequest);
        boolean parkingIdStatus = carParkRepository.validateParkingSlotId(parkingSlotId);

    }


}
