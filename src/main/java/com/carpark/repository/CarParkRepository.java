package com.carpark.repository;

import com.carpark.exception.InvalidDurationException;
import com.carpark.exception.NoParkingSpaceException;
import com.carpark.model.ParkingSlot;
import com.carpark.model.ParkingSlotRequest;
import com.carpark.utility.Constants;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarParkRepository {

    private List<ParkingSlot> parkingSlots;
    private int index;

    public CarParkRepository() {
        this.parkingSlots = new ArrayList<>();
        this.index=0;
    }

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
        ParkingSlot parkingSlot = parkingSlots.get(id);
        parkingSlots.removeIf(o -> o.getId() == id);
        return parkingSlot;
    }
}
