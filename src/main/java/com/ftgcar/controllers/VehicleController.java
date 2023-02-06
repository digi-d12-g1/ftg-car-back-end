package com.ftgcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/add")
    public VehicleDto addVehicle(VehicleDto vehicleDto) {
        return vehicleService.addVehicle(vehicleDto);
    }

    @GetMapping("/findAll")
    public List<VehicleDto> findAllVehicles() {
        return vehicleService.findAllVehicles();
    }
}
