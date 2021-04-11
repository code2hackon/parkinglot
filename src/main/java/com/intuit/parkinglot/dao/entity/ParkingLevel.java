package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.enums.SpotType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class ParkingLevel implements Serializable {

    private Integer floor;
    private ParkingSpot[] spots;
    private int availableSpots;

    private static Logger log = LoggerFactory.getLogger(ParkingLevel.class);

    public ParkingLevel(int floor, int numberOfSpots){
        this.floor = floor;
        this.availableSpots = numberOfSpots;
        this.spots = new ParkingSpot[numberOfSpots];
        initializeSpots(numberOfSpots);
    }

    private void initializeSpots(int numberOfSpots){

        int largeSpots = (int) (numberOfSpots * Constants.PERCENTAGE_OF_LARGE_SPOTS);
        int compactSpots = (int) (numberOfSpots * Constants.PERCENTAGE_OF_COMPACT_SPOTS);
        int motorcycleSpots = numberOfSpots - largeSpots - compactSpots;

        for (int spotNumber = 0; spotNumber < numberOfSpots; spotNumber++){
            SpotType spotType = SpotType.MOTORCYCLE;
            if (spotNumber < largeSpots){
                spotType = SpotType.LARGE;
            }
            else if (spotNumber < (largeSpots + compactSpots)){
                spotType = SpotType.COMPACT;
            }
            int row = spotNumber / Constants.SPOT_PER_ROW;
            spots[spotNumber] = new ParkingSpot(this, row, spotNumber, spotType);
        }

        log.info("Spots created in level {} motorcycle:{} compact:{} large:{} ", floor,motorcycleSpots,compactSpots,largeSpots);
    }

    public int getAvailableSpots(){
        return availableSpots;
    }


    public boolean parkVehicle(Vehicle vehicle){
        if (getAvailableSpots() < vehicle.getSpotsNeeded())
            return false;

        int spotNumber = findAvailableSpots(vehicle);
        if (spotNumber < 0)
            return false;
        return parkStartingAtSpot(spotNumber, vehicle);
    }


    private int findAvailableSpots(Vehicle vehicle){
        int numberOfSpotsNeeded = vehicle.getSpotsNeeded();
        int lastRow = -1;
        int matchingSpotsCount = 0;

        for (int i = 0; i < spots.length; i++){
            ParkingSpot spot = spots[i];
            if (lastRow != spot.getRow()){
                matchingSpotsCount = 0;
                lastRow = spot.getRow();
            }
            if (spot.canFitVehicle(vehicle)){
                matchingSpotsCount++;
            }
            else{
                matchingSpotsCount = 0;
            }
            if (matchingSpotsCount == numberOfSpotsNeeded){
                return i - (numberOfSpotsNeeded - 1);
            }
        }
        return -1;
    }

    private boolean parkStartingAtSpot(int num, Vehicle vehicle){
        vehicle.clearSpots();
        boolean success = true;
        for (int i = num; i < num + vehicle.spotsNeeded; i++){
            success &= spots[i].park(vehicle);
        }
        availableSpots -= vehicle.spotsNeeded;
        return success;
    }

    public void notifySpotFreed() {
        availableSpots++;
    }
}
