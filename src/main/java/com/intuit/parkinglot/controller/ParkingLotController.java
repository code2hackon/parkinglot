package com.intuit.parkinglot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/vehicle")
public class ParkingLotController {

    private static Logger log = LoggerFactory.getLogger(ParkingLotController.class);

    @GetMapping(value = "/park")
    public String parkVehicle(){
        log.info("Called API parkVehicle");
        return "Parked success";
    }
}
