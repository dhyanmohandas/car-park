package com.carparking.service;

import com.carparking.exception.InvalidDurationException;
import com.carparking.exception.NoParkingSpaceException;
import com.carparking.exception.ParkingSlotNotFound;
import com.carparking.model.ParkingSlot;
import com.carparking.model.ParkingSlotRequest;
import com.carparking.repository.CarParkRepository;
import com.carparking.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

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
