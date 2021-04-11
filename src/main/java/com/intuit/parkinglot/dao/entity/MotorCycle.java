package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.enums.SpotType;
import com.intuit.parkinglot.dao.enums.VehicleType;

import java.io.Serializable;

public class MotorCycle extends Vehicle implements Serializable {

    public MotorCycle(){
        spotsNeeded = Constants.SPOT_SIZE_FOR_MOTORCYCLE;
        vehicleType = VehicleType.MOTORCYCLE;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getSpotType() == SpotType.MOTORCYCLE || parkingSpot.getSpotType() == SpotType.COMPACT
                || parkingSpot.getSpotType() == SpotType.LARGE;
    }
}
