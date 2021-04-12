package com.intuit.parkinglot.dto;

import com.intuit.parkinglot.dao.enums.VehicleType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VehicleParkRequest implements Serializable {

    @NotNull(message = "Registration number should not blank")
    private String registrationNumber;

    @NotNull(message = "Vehicle type should not blank")
    private VehicleType vehicleType;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
