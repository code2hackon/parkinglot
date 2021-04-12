package com.intuit.parkinglot.dto;

import com.intuit.parkinglot.dao.entity.ParkingSpot;
import com.intuit.parkinglot.dao.enums.VehicleType;

import java.util.List;

public class VehicleParkResponse extends BaseResponse{

    protected String registrationNumber;

    protected String colour;

    protected VehicleType vehicleType;

    protected Integer spotsNeeded;

    protected List<SpotDetails> parkingSpots;

    public VehicleParkResponse(String registrationNumber, String colour, VehicleType vehicleType, Integer spotsNeeded, List<SpotDetails> parkingSpots) {
        super();
        this.registrationNumber = registrationNumber;
        this.colour = colour;
        this.vehicleType = vehicleType;
        this.spotsNeeded = spotsNeeded;
        this.parkingSpots = parkingSpots;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getSpotsNeeded() {
        return spotsNeeded;
    }

    public void setSpotsNeeded(Integer spotsNeeded) {
        this.spotsNeeded = spotsNeeded;
    }

    public List<SpotDetails> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<SpotDetails> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}
