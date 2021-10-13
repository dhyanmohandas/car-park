package com.carpark.controller;

import com.carpark.model.ParkingSlot;
import com.carpark.model.ParkingSlotRequest;
import com.carpark.service.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarParkController {

    @Autowired
    CarParkService carParkService;

    @PostMapping(value = "/parkingSlots")
    public ParkingSlot createParkingSlot(@RequestBody ParkingSlotRequest parkingSlotRequest){
        return carParkService.createParkingSlot(parkingSlotRequest);
    }
    @PutMapping(value = "/parkingSlots/{parkingSlotId}")
    public ParkingSlot updateParkingSlot(@PathVariable int parkingSlotId, @RequestBody ParkingSlotRequest parkingSlotRequest){
        return carParkService.updateParkingSlot(parkingSlotId, parkingSlotRequest);
    }

    @DeleteMapping(value = "/parkingSlots/{parkingSlotId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteParkingSlotId(@PathVariable int parkingSlotId){
        carParkService.deleteParkingSlot(parkingSlotId);
    }
}
