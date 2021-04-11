package com.intuit.parkinglot.service;

import com.intuit.parkinglot.dao.entity.Vehicle;
import com.intuit.parkinglot.exception.ParkingLotException;

import java.util.List;

public interface IParkingService {

    public void createParkingLot(int numberOfSlots, int numberOfLevels) throws ParkingLotException;

    public boolean parkVehicle(Vehicle vehicle) throws ParkingLotException;

    public boolean leaveVehicle(String  registrationNumber);

    public Vehicle getVehicleDetailsByRegistrationNumber(String  registrationNumber);

    public List<Integer> getSpotNumbersByRegistrationNo(String registrationNo);

}
