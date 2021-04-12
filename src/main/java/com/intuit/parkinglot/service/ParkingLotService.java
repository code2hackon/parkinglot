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
    public void createParkingLot(int numberOfParkingSlots, int numberOfLevels, int rows) throws ParkingLotException {

        if(ParkingLot.isCreatedInstance())
            throw new ParkingLotException(Constants.PARKING_LOT_ALREADY_CREATED);

        parkingLot = ParkingLot.getInstance(numberOfParkingSlots, numberOfLevels, rows);
        System.out.println("Created parking lot with " + numberOfParkingSlots + " slots");
    }

    @Override
    public boolean parkVehicle(VehicleParkRequest vehicleParkRequest) throws ParkingLotException {
        if(parkingLot == null) {
            log.error("Parking lot not created");
            throw new ParkingLotException(Constants.PARKING_LOT_NOT_CREATED);
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
            throw new ParkingLotException(Constants.PARKING_LOT_NOT_CREATED);
        }
        return parkingLot.leaveVehicle(registrationNumber);
    }

    @Override
    public Vehicle getVehicleDetailsByRegistrationNumber(String  registrationNumber) {
        if(parkingLot == null) {
            log.error("Parking lot not created");
            throw new ParkingLotException(Constants.PARKING_LOT_NOT_CREATED);
        }
        return parkingLot.getVehicleDetailsByRegdNumber(registrationNumber);
    }

}
