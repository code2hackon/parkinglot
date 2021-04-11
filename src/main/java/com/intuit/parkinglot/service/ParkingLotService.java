package com.intuit.parkinglot.service;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.entity.ParkingLot;
import com.intuit.parkinglot.dao.entity.Vehicle;
import com.intuit.parkinglot.exception.ParkingLotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParkingLotService implements  IParkingService{

    protected ParkingLot parkingLot;

    private static Logger log = LoggerFactory.getLogger(ParkingLotService.class);

    @Override
    public void createParkingLot(int numberOfParkingSlots, int numberOfLevels) throws ParkingLotException {

        if(ParkingLot.isCreatedInstance())
            throw new ParkingLotException(Constants.PARKING_LOT_ALREADY_CREATED);

        parkingLot = ParkingLot.getInstance(numberOfParkingSlots, numberOfLevels);
        System.out.println("Created parking lot with " + numberOfParkingSlots + " slots");
    }

    @Override
    public boolean parkVehicle(Vehicle vehicle) throws ParkingLotException {
        return parkingLot.parkVehicle(vehicle);
    }

    @Override
    public boolean leaveVehicle(String  registrationNumber) throws ParkingLotException {
        return parkingLot.leaveVehicle(registrationNumber);
    }

    @Override
    public Vehicle getVehicleDetailsByRegistrationNumber(String  registrationNumber) {
        return parkingLot.getVehicleDetailsByRegdNumber(registrationNumber);
    }

    @Override
    public List<Integer> getSpotNumbersByRegistrationNo(String registrationNo) {
        List<Integer> spots = null;
        Vehicle vehicle =  parkingLot.getVehicleDetailsByRegdNumber(registrationNo);
        if(vehicle != null){
            spots = vehicle.getParkingSpots().stream().map(item -> item.getSpotNumber()).collect(Collectors.toList());
            log.info("Vehicle with registration number {} parked in spots: {}",vehicle.getRegistrationNumber(),spots);
        }
        return spots;
    }

}
