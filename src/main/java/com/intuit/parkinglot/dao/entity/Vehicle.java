package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.dao.enums.VehicleType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Vehicle implements Serializable {

    protected String registrationNumber;

    protected String colour;

    protected VehicleType vehicleType;

    protected Integer spotsNeeded;

    protected List<ParkingSpot> parkingSpots;

    public Vehicle(String registrationNumber) {
    	this.registrationNumber = registrationNumber;
        parkingSpots = new LinkedList<ParkingSpot>();
    }

    public abstract boolean canFitInSpot(ParkingSpot spot);

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColour() {
        return colour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Integer getSpotsNeeded() {
        return spotsNeeded;
    }

    public void setSpotsNeeded(Integer spotsNeeded) {
        this.spotsNeeded = spotsNeeded;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void parkInSpot(ParkingSpot spot){
        parkingSpots.add(spot);
    }

    public void clearSpots(){
        for (int i = 0; i < parkingSpots.size(); i++){
            parkingSpots.get(i).removeVehicle();
        }
        parkingSpots.clear();
    }


}
