package com.intuit.parkinglot.exception;

public class ParkingLotException extends RuntimeException{

    public ParkingLotException(String message) {
        super(message);
    }

    public ParkingLotException(Throwable th) {
        super(th);
    }

    public ParkingLotException(String message, Throwable th) {
        super(message,th);
    }

}
