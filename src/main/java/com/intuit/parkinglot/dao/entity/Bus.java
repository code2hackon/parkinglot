package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.enums.SpotType;
import com.intuit.parkinglot.dao.enums.VehicleType;

import java.io.Serializable;

public class Bus extends Vehicle implements Serializable {

    public Bus(String registrationNumber){
    	super(registrationNumber);
        spotsNeeded = Constants.SPOT_SIZE_FOR_BUS;
        vehicleType = VehicleType.BUS;
    }


    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getSpotType() == SpotType.LARGE;
    }
}
