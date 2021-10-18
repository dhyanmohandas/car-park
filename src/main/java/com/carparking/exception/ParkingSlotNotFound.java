package com.carparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParkingSlotNotFound extends RuntimeException {
    public ParkingSlotNotFound(String msg){
        super(msg);
    }
}
