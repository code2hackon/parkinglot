package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.enums.SpotType;
import com.sun.corba.se.impl.interceptors.SlotTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLevel implements Serializable {

    private Integer floor;
    private ParkingSpot[] spots;
    private int availableSpots;
    private Map<SpotType,Integer> spotCountMap;
    private Map<SpotType, SpotIndex> spotIndexMap;
    private static Logger log = LoggerFactory.getLogger(ParkingLevel.class);

    public ParkingLevel(int floor, int numberOfSpots){
        this.floor = floor;
        this.availableSpots = numberOfSpots;
        this.spots = new ParkingSpot[numberOfSpots];
        this.spotCountMap = new ConcurrentHashMap<SpotType,Integer>();
        this.spotIndexMap = new ConcurrentHashMap<SpotType,SpotIndex>();
        initializeSpots(numberOfSpots);
    }

    private void initializeSpots(int numberOfSpots){

        int largeSpots = (int) (numberOfSpots * Constants.PERCENTAGE_OF_LARGE_SPOTS);
        int compactSpots = (int) (numberOfSpots * Constants.PERCENTAGE_OF_COMPACT_SPOTS);
        int motorcycleSpots = numberOfSpots - largeSpots - compactSpots;
        updateSpotCountMap(largeSpots,compactSpots,motorcycleSpots);

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

        spotIndexMap.put(SpotType.LARGE , new SpotIndex(0, largeSpots-1));
        spotIndexMap.put(SpotType.COMPACT, new SpotIndex(largeSpots,largeSpots+compactSpots-1));
        spotIndexMap.put(SpotType.MOTORCYCLE, new SpotIndex(largeSpots+compactSpots,numberOfSpots-1));
        log.info("Bus spots : [{}-{}] , Car spots : [{}-{}] , Motorcycle Spots : [{}-{}]",
                0,largeSpots-1,largeSpots,(largeSpots+compactSpots-1),largeSpots+compactSpots,numberOfSpots-1);
        log.info("Spots created in level {} large:{} compact:{} motorcycle:{} ", floor,largeSpots,compactSpots,motorcycleSpots);
    }

    private void updateSpotCountMap(int largeSpots, int compactSpots, int motorcycleSpots) {
        spotCountMap.put(SpotType.LARGE,largeSpots);
        spotCountMap.put(SpotType.COMPACT,compactSpots);
        spotCountMap.put(SpotType.MOTORCYCLE,motorcycleSpots);
    }


    public int getAvailableSpots(){
        return availableSpots;
    }


    public boolean parkVehicle(Vehicle vehicle, SpotType spotType){
        if (getAvailableSpots() < vehicle.getSpotsNeeded() || spotCountMap.get(spotType) == 0)
            return false;

        int spotNumber = findAvailableSpots(vehicle, spotType);
        if (spotNumber < 0)
            return false;
        return parkStartingAtSpot(spotNumber, vehicle);
    }


    private int findAvailableSpots(Vehicle vehicle, SpotType spotType){
        int numberOfSpotsNeeded = vehicle.getSpotsNeeded();
        int lastRow = -1;
        int matchingSpotsCount = 0;
        SpotIndex spotIndex = spotIndexMap.get(spotType);
        for (int i = spotIndex.getBegin(); i <= spotIndex.getEnd(); i++){
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

    private boolean parkStartingAtSpot(int spotIndex, Vehicle vehicle){
        vehicle.clearSpots();
        boolean success = true;
        for (int i = spotIndex; i < spotIndex + vehicle.spotsNeeded; i++){
            success &= spots[i].park(vehicle);
            SpotType spotType = spots[i].getSpotType();
            spotCountMap.put(spotType,spotCountMap.get(spotType)+1);
        }
        availableSpots -= vehicle.spotsNeeded;
        return success;
    }

    public int getFloor(){
        return this.floor;
    }

    public void notifySpotFreed(SpotType spotType) {
        spotCountMap.put(spotType,spotCountMap.get(spotType)-1);
        availableSpots++;
    }
}
