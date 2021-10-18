package com.carparking.repository;

import com.carparking.model.ParkingSlot;
import com.carparking.model.ParkingSlotRequest;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarParkRepository {

    private List<ParkingSlot> parkingSlots =new ArrayList<>();
    private int index;


    public int getParkingSlotAllocated() {
        return parkingSlots.size();
    }

    public ParkingSlot createParkingSlot(ParkingSlotRequest parkingSlotRequest) {
        ParkingSlot parkingSlot = new ParkingSlot(++index, parkingSlotRequest.getVehicleNumber(), parkingSlotRequest.getDurationInHours(), Timestamp.from(Instant.now()));
        parkingSlots.add(parkingSlot);
        return parkingSlot;
    }

    public ParkingSlot updateParkingSlot(int parkingSlotId, ParkingSlotRequest parkingSlotRequest) {
        return null;
    }

    public boolean validateParkingSlotId(int parkingSlotId) {
        boolean status = parkingSlots.stream().anyMatch(o -> o.getId() == parkingSlotId);
        return status;
    }

    public ParkingSlot deleteParkingSlot(int id) {
        ParkingSlot parkingSlot = parkingSlots.stream().filter(o->o.getId()==id).findAny().orElse(null);
        parkingSlots.removeIf(o -> o.getId() == id);
        return parkingSlot;
    }
}
