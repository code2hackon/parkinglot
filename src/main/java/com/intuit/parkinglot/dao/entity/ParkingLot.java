package com.intuit.parkinglot.dao.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingLot implements Serializable {

    private ParkingLevel[] levels;
    private final int NUM_LEVELS;
    private static volatile ParkingLot parkingLot = null;
    private static volatile boolean createdInstance = false;
    private Map<String,Vehicle> mapOfRegdNoVsVehicle;

    private static Logger log = LoggerFactory.getLogger(ParkingLot.class);

    private ParkingLot(int numberOfSpots, int numberOfLevels){
        NUM_LEVELS = numberOfLevels;
        levels = new ParkingLevel[NUM_LEVELS];
        mapOfRegdNoVsVehicle = new ConcurrentHashMap<String, Vehicle>();
        for (int floor = 0; floor < NUM_LEVELS; floor++){
            levels[floor] = new ParkingLevel(floor, numberOfSpots);

        }
    }


    public static synchronized ParkingLot getInstance(int numberOfParkingSlots, int numberOfLevels) {
        if(parkingLot == null) {
            synchronized (ParkingLot.class){
                if(parkingLot == null) {
                    parkingLot = new ParkingLot(numberOfParkingSlots, numberOfLevels);
                    createdInstance = true;
                }
            }
        }
        return parkingLot;
    }

    public static boolean isCreatedInstance(){
        return createdInstance == true;
    }

    public boolean parkVehicle(Vehicle vehicle){
        for (int i = 0; i < levels.length; i++){
            if (levels[i].parkVehicle(vehicle)) {
                mapOfRegdNoVsVehicle.put(vehicle.getRegistrationNumber(), vehicle);
                List<Integer> spots = vehicle.getParkingSpots().stream().map(item -> item.getSpotNumber()).collect(Collectors.toList());
                log.info("Vehicle with registration number {} parked in spots: {}",vehicle.getRegistrationNumber(),spots);
                return true;
            }
        }
        return false;
    }

    public boolean leaveVehicle(String regdNumber){
        Vehicle vehicle = mapOfRegdNoVsVehicle.get(regdNumber);
        if(vehicle == null ) {
            log.info("Vehicle with registration number {} not found", regdNumber);
            return false;
        }
        else{
            vehicle.clearSpots();
            mapOfRegdNoVsVehicle.remove(regdNumber);
            log.info("Vehicle with registration number {} is unparked successfully.", regdNumber);
        }
        return true;
    }

    public Vehicle getVehicleDetailsByRegdNumber(String regdNumber){
        Vehicle vehicle = mapOfRegdNoVsVehicle.get(regdNumber);
        if(vehicle == null ) {
            log.info("Vehicle with registration number {} not found", regdNumber);
            return null;
        }
        else {
           return vehicle;
        }
    }

}
