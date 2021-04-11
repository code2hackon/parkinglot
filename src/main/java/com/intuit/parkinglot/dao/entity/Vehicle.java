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

    public Vehicle() {
        parkingSpots = new LinkedList<ParkingSpot>();
    }

    public Vehicle(String registrationNumber, String colour) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }

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

    public abstract boolean canFitInSpot(ParkingSpot spot);

    @Override public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) obj;
        return (registrationNumber == vehicle.registrationNumber || (registrationNumber != null && registrationNumber.equals(vehicle.getRegistrationNumber()))) &&
                (colour == vehicle.colour || (colour != null && colour .equals(vehicle.getColour()))) &&
                (vehicleType == vehicle.vehicleType || (vehicleType != null && vehicleType.equals(vehicle.getVehicleType())));

    }

    @Override public int hashCode() {
        final int prime = 31; int result = 1;
        result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
        result = prime * result + ((vehicleType == null) ? 0 :vehicleType.hashCode());
        result = prime * result + ((colour == null) ? 0 : colour.hashCode());
        return result;

    }


}
