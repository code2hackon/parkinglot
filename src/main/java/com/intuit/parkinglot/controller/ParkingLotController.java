package com.intuit.parkinglot.controller;


import com.intuit.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/vehicle")
public class ParkingLotController {


    private static Logger log = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping(value = "/create")
    public String parkVehicle(){
        parkingLotService.createParkingLot(50,3);
        log.info("Called API parkVehicle");
        return "Parked success";
    }

}
