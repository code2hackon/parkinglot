package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.dao.enums.SpotType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ParkingLot implements Serializable {

    private ParkingLevel[] levels;
    private final int NUM_LEVELS;
    private static volatile ParkingLot parkingLot = null;
    private static volatile boolean createdInstance = false;
    private Map<String,Vehicle> mapOfRegdNoVsVehicle;
    private LinkedList<SpotType> spotTypeOrderedLinkedList;

    private static Logger log = LoggerFactory.getLogger(ParkingLot.class);

    private ParkingLot(int numberOfSpots, int numberOfLevels, int rows){
        NUM_LEVELS = numberOfLevels;
        levels = new ParkingLevel[NUM_LEVELS];
        mapOfRegdNoVsVehicle = new ConcurrentHashMap<String, Vehicle>();
        for (int floor = 0; floor < NUM_LEVELS; floor++){
            levels[floor] = new ParkingLevel(floor, numberOfSpots, rows);
        }
        spotTypeOrderedLinkedList = new LinkedList<SpotType>();
        spotTypeOrderedLinkedList.add(SpotType.MOTORCYCLE);
        spotTypeOrderedLinkedList.add(SpotType.COMPACT);
        spotTypeOrderedLinkedList.add(SpotType.LARGE);
    }


    public static synchronized ParkingLot getInstance(int numberOfParkingSlots, int numberOfLevels, int rows) {
        if(parkingLot == null) {
            synchronized (ParkingLot.class){
                if(parkingLot == null) {
                    parkingLot = new ParkingLot(numberOfParkingSlots, numberOfLevels, rows);
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
        for(SpotType spotType : spotTypeOrderedLinkedList) {
            for (int i = 0; i < levels.length; i++) {
                if (levels[i].parkVehicle(vehicle, spotType)) {
                    mapOfRegdNoVsVehicle.put(vehicle.getRegistrationNumber(), vehicle);
                    List<Integer> spots = vehicle.getParkingSpots().stream().map(item -> item.getSpotNumber()).collect(Collectors.toList());
                    int row = vehicle.getParkingSpots().get(0).getRow();
                    int level = vehicle.getParkingSpots().get(0).getLevel().getFloor();
                    log.info("Vehicle with registration number {} parked in spots: {} row:{} level:{}", vehicle.getRegistrationNumber(), spots, row, level);
                    return true;
                }
            }
        }
        log.info("Vehicle with registration number {} parking failed. No empty slot available",vehicle.getRegistrationNumber());
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
