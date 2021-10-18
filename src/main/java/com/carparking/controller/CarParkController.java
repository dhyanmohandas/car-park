package com.carparking.controller;

import com.carparking.model.ParkingSlot;
import com.carparking.model.ParkingSlotRequest;
import com.carparking.service.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarParkController {

    @Autowired
    CarParkService carParkService;

    @PostMapping(value = "/parkingslots")
    public ParkingSlot createParkingSlot(@RequestBody ParkingSlotRequest parkingSlotRequest){
        return carParkService.createParkingSlot(parkingSlotRequest);
    }
    @PutMapping(value = "/parkingslots/{parkingSlotId}")
    public ParkingSlot updateParkingSlot(@PathVariable int parkingSlotId, @RequestBody ParkingSlotRequest parkingSlotRequest){
        return carParkService.updateParkingSlot(parkingSlotId, parkingSlotRequest);
    }

    @DeleteMapping(value = "/parkingslots/{parkingSlotId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteParkingSlotId(@PathVariable int parkingSlotId){
        carParkService.deleteParkingSlot(parkingSlotId);
    }
}
