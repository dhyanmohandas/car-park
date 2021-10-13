package com.carpark.service;

import com.carpark.exception.InvalidDurationException;
import com.carpark.exception.NoParkingSpaceException;
import com.carpark.exception.ParkingSlotNotFound;
import com.carpark.model.ParkingSlot;
import com.carpark.model.ParkingSlotRequest;
import com.carpark.repository.CarParkRepository;
import com.carpark.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class CarParkService {

    @Autowired
    CarParkRepository carParkRepository;

    public CarParkService(CarParkRepository carParkRepository) {
        this.carParkRepository = carParkRepository;
    }

    public ParkingSlot createParkingSlot(ParkingSlotRequest parkingSlotRequest){
        if (carParkRepository.getParkingSlotAllocated() >= Constants.PARKING_SPACE_SIZE) {
            throw new NoParkingSpaceException();
        }
        if (parkingSlotRequest.getDurationInHours() > Constants.DURATION_UPPER_LIMIT || parkingSlotRequest.getDurationInHours() < Constants.DURATION_LOWER_LIMIT) {
            throw new InvalidDurationException();
        }
        return carParkRepository.createParkingSlot(parkingSlotRequest);
    }

    public ParkingSlot updateParkingSlot(int parkingSlotId, ParkingSlotRequest parkingSlotRequest) {
        if(!carParkRepository.validateParkingSlotId(parkingSlotId))
            throw new ParkingSlotNotFound("Parking Slot not found");
        return carParkRepository.updateParkingSlot(parkingSlotId,parkingSlotRequest);
    }

    public ParkingSlot deleteParkingSlot(int id){
        if(!carParkRepository.validateParkingSlotId(id)){
            throw new ParkingSlotNotFound("Parking Slot not found");
        }
        return carParkRepository.deleteParkingSlot(id);
    }
}
