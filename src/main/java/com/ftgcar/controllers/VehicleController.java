package com.ftgcar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftgcar.dto.VehicleDto;
import com.ftgcar.services.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public VehicleDto addVehicle(VehicleDto vehicleDto) {
        return vehicleService.addVehicle(vehicleDto);

    }

}
