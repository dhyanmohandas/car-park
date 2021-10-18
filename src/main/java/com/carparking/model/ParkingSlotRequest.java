package com.carparking.model;

public class ParkingSlotRequest {
    String vehicleNumber;
    int durationInHours;

    public ParkingSlotRequest(String vehicleNumber, int durationInHours) {
        this.vehicleNumber = vehicleNumber;
        this.durationInHours = durationInHours;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
