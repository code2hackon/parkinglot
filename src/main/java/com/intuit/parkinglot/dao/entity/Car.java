package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.enums.SpotType;
import com.intuit.parkinglot.dao.enums.VehicleType;

import java.io.Serializable;

public class Car extends Vehicle implements Serializable {

    public Car(String registrationNumber){
    	super(registrationNumber);
        spotsNeeded = Constants.SPOT_SIZE_FOR_CAR;
        vehicleType = VehicleType.CAR;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return  parkingSpot.getSpotType() == SpotType.COMPACT || parkingSpot.getSpotType() == SpotType.LARGE;
    }
}
