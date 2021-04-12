package com.intuit.parkinglot.controller;


import com.intuit.parkinglot.constant.Constants;
import com.intuit.parkinglot.dao.entity.Vehicle;
import com.intuit.parkinglot.dto.BaseResponse;
import com.intuit.parkinglot.dto.VehicleParkRequest;
import com.intuit.parkinglot.dto.VehicleParkResponse;
import com.intuit.parkinglot.exception.ValidationException;
import com.intuit.parkinglot.service.ParkingLotService;
import com.intuit.parkinglot.utilities.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class ParkingLotController {


    private static Logger log = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping(value = "/create")
    public ResponseEntity<String> createParkingLot(@RequestParam("slot") Integer slot, @RequestParam("level") Integer level, @RequestParam("row") Integer row) throws Exception{
        if(slot == null || level == null)
            throw new ValidationException("Slot or level is null.");
        parkingLotService.createParkingLot(slot,level, row);
        return new ResponseEntity<String>("Parking Lot Created", HttpStatus.CREATED);
    }

    @PostMapping(value = "/park")
    public ResponseEntity<BaseResponse> parkVehicle(@RequestBody @Valid VehicleParkRequest vehicle, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        log.info("Called API parkVehicle {} {}", vehicle.getRegistrationNumber(), vehicle.getVehicleType());

        Boolean isParked = parkingLotService.parkVehicle(vehicle);
        BaseResponse baseResponse = new BaseResponse();
        if(isParked){
            baseResponse.setStatusCode(Constants.SUCCESS);
            baseResponse.setMessageDescription(Constants.VEHICLE_PARK_SUCCESS);
        }else{
            baseResponse.setStatusCode(Constants.FAILURE);
            baseResponse.setMessageDescription(Constants.VEHICLE_PARK_FAILURE);
        }
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/leave")
    public ResponseEntity<BaseResponse> leaveVehicle(@RequestParam("regdNo") String registrationNumber)  {
        if(registrationNumber == null)
            throw new ValidationException("RegistrationNumber is null or empty.");
        BaseResponse baseResponse = new BaseResponse();
        Boolean result = parkingLotService.leaveVehicle(registrationNumber);
        if(result){
            baseResponse.setStatusCode(Constants.SUCCESS);
            baseResponse.setMessageDescription(Constants.SUCCESS_MESSAGE);
        }else{
            baseResponse.setStatusCode(Constants.FAILURE);
            baseResponse.setMessageDescription(Constants.VEHICLE_NOT_FOUND);
        }
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/details")
    public ResponseEntity<BaseResponse> getVehicleDetailsByRegistrationNumber(@RequestParam("regdNo") String registrationNumber) throws Exception{
        if(registrationNumber == null)
            throw new ValidationException("RegistrationNumber is null or empty.");
        Vehicle vehicle = parkingLotService.getVehicleDetailsByRegistrationNumber(registrationNumber);
        if(vehicle == null){
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(Constants.FAILURE);
            baseResponse.setMessageDescription(Constants.FAILURE_MESSAGE);
            return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
        }
        VehicleParkResponse response = Utility.convertToVehicleParkResponse(vehicle);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

}
