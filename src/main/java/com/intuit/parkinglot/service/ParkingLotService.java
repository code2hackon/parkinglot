package com.intuit.parkinglot.service;

import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.entity.*;
import com.intuit.parkinglot.dto.VehicleParkRequest;
import com.intuit.parkinglot.exception.ParkingLotException;
import com.intuit.parkinglot.exception.ValidationException;
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
    public void createParkingLot(Integer numberOfParkingSlots, Integer numberOfLevels) throws ParkingLotException {

        if(ParkingLot.isCreatedInstance())
            throw new ParkingLotException(Constants.PARKING_LOT_ALREADY_CREATED);

        parkingLot = ParkingLot.getInstance(numberOfParkingSlots, numberOfLevels);
        System.out.println("Created parking lot with " + numberOfParkingSlots + " slots");
    }

    @Override
    public boolean parkVehicle(VehicleParkRequest vehicleParkRequest) throws ParkingLotException {
        if(parkingLot == null) {
            log.error("Parking lot not created");
            return false;
        }

        Vehicle vehicle;
        String regdNumber = vehicleParkRequest.getRegistrationNumber();
        if(parkingLot.getVehicleDetailsByRegdNumber(regdNumber) != null) {
        	log.error("Vehicle with registration number {} already parked",regdNumber);
        	return false;
        }
        
        switch (vehicleParkRequest.getVehicleType()){
            case MOTORCYCLE:{
                vehicle = new MotorCycle(regdNumber);
                break;
            }
            case CAR:{
                vehicle = new Car(regdNumber);
                break;
            }
            case BUS:{
                vehicle = new Bus(regdNumber);
                break;
            }
            default:
                throw new ValidationException("Invalid Vehicle type:"+vehicleParkRequest.getVehicleType());
        }
        return parkingLot.parkVehicle(vehicle);
    }

    @Override
    public boolean leaveVehicle(String  registrationNumber) throws ParkingLotException {
        if(parkingLot == null) {
            log.error("Parking lot not created");
            return false;
        }
        return parkingLot.leaveVehicle(registrationNumber);
    }

    @Override
    public Vehicle getVehicleDetailsByRegistrationNumber(String  registrationNumber) {
        if(parkingLot == null) {
            log.error("Parking lot not created");
            return null;
        }
        return parkingLot.getVehicleDetailsByRegdNumber(registrationNumber);
    }

    @Override
    public List<Integer> getSpotNumbersByRegistrationNo(String registrationNo) {
        if(parkingLot == null) {
            log.error("Parking lot not created");
            return null;
        }
        List<Integer> spots = null;
        Vehicle vehicle =  parkingLot.getVehicleDetailsByRegdNumber(registrationNo);
        if(vehicle != null){
            spots = vehicle.getParkingSpots().stream().map(item -> item.getSpotNumber()).collect(Collectors.toList());
            log.info("Vehicle with registration number {} parked in spots: {}",vehicle.getRegistrationNumber(),spots);
        }
        return spots;
    }

}
