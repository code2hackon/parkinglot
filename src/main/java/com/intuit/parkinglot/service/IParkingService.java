package com.intuit.parkinglot.service;

import com.intuit.parkinglot.dao.entity.Vehicle;
import com.intuit.parkinglot.dto.VehicleParkRequest;
import com.intuit.parkinglot.exception.ParkingLotException;

public interface IParkingService {

    void createParkingLot(int numberOfSlots, int numberOfLevels, int rows) throws ParkingLotException;

    boolean parkVehicle(VehicleParkRequest vehicle) throws ParkingLotException;

    boolean leaveVehicle(String  registrationNumber);

    Vehicle getVehicleDetailsByRegistrationNumber(String  registrationNumber);

}
