package com.carpark.model;

import java.sql.Timestamp;

public class ParkingSlot {

    int id;
    String vehicleNumber;
    int durationInHours;
    Timestamp startTime;

    public ParkingSlot(int id, String vehicleNumber, int durationInHours, Timestamp startTime) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.durationInHours = durationInHours;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
