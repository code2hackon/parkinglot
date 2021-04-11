package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.dao.enums.SpotType;

public class ParkingSpot {

    private SpotType spotType;
    private Vehicle vehicle;
    private Integer row;
    private Integer spotNumber;
    private ParkingLevel level;

    public ParkingSpot(ParkingLevel level, int row, int spotNumber, SpotType spotType){
        this.level = level;
        this.row = row;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
    }

    public boolean isAvailable(){
        return this.vehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public ParkingLevel getLevel() {
        return level;
    }

    public boolean park(Vehicle v){
        if (!canFitVehicle(v))
            return false;

        vehicle = v;
        vehicle.parkInSpot(this);
        return true;
    }

    public void removeVehicle() {
        level.notifySpotFreed();
        vehicle = null;
    }
}
