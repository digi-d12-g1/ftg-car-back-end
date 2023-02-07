package com.ftgcar.controllers;

import com.ftgcar.dto.VehicleDto;
import com.ftgcar.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public VehicleDto addVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleService.addVehicle(vehicleDto);
    }

    @GetMapping("/findAll")
    public List<VehicleDto> findAllVehicles() {

        return vehicleService.findAllVehicles();
    }
}
