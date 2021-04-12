package com.intuit.parkinglot.utilities;

import com.intuit.parkinglot.dao.entity.Vehicle;
import com.intuit.parkinglot.dto.SpotDetails;
import com.intuit.parkinglot.dto.VehicleParkResponse;

import java.util.List;
import java.util.stream.Collectors;

public class Utility {

    public static VehicleParkResponse convertToVehicleParkResponse(Vehicle vehicle){

        List<SpotDetails> spotDetails= vehicle.getParkingSpots().stream().map(v -> new SpotDetails(v.getLevel().getFloor(),v.getRow(),v.getSpotType())).collect(Collectors.toList());
        VehicleParkResponse response = new VehicleParkResponse(vehicle.getRegistrationNumber(), vehicle.getColour(), vehicle.getVehicleType(),
                vehicle.getSpotsNeeded(),spotDetails);
        return  response;

    }
}
