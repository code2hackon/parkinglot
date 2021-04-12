package com.intuit.parkinglot.service;

import com.intuit.parkinglot.dao.entity.Vehicle;
import com.intuit.parkinglot.dto.VehicleParkRequest;
import com.intuit.parkinglot.exception.ParkingLotException;

import java.util.List;

public interface IParkingService {

    void createParkingLot(Integer numberOfSlots, Integer numberOfLevels) throws ParkingLotException;

    boolean parkVehicle(VehicleParkRequest vehicle) throws ParkingLotException;

    boolean leaveVehicle(String  registrationNumber);

    Vehicle getVehicleDetailsByRegistrationNumber(String  registrationNumber);

    List<Integer> getSpotNumbersByRegistrationNo(String registrationNo);

}
