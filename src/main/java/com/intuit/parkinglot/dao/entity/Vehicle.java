package com.intuit.parkinglot.dao.entity;

import com.intuit.parkinglot.dao.enums.VehicleType;

public abstract class Vehicle {


    private String registrationNumber;
    private String colour;
    private ParkingTicket parkingTicket;
    private VehicleType vehicleType;

    public Vehicle() {

    }

    public Vehicle(String registrationNumber, String colour, VehicleType vehicleType) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
        this.parkingTicket = new ParkingTicket();
        this.setVehicleType(vehicleType);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

//    public void setRegistrationNumber(String registrationNumber) {
//        this.registrationNumber = registrationNumber;
//    }

    public String getColour() {
        return colour;
    }

//    public void setColour(String colour) {
//        this.colour = colour;
//    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) obj;
        return (registrationNumber == vehicle.registrationNumber || (registrationNumber != null && registrationNumber.equals(vehicle.getRegistrationNumber()))) &&
                (colour == vehicle.colour || (colour != null && colour .equals(vehicle.getColour()))) &&
                (vehicleType == vehicle.vehicleType || (vehicleType != null && vehicleType.equals(vehicle.getVehicleType())));

    }

    @Override public int hashCode() {
        final int prime = 31; int result = 1;
        result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
        result = prime * result + ((vehicleType == null) ? 0 :vehicleType.hashCode());
        result = prime * result + ((colour == null) ? 0 : colour.hashCode());
        return result;

    }


}
