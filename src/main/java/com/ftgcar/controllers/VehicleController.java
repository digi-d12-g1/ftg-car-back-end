package com.ftgcar.controllers;

import com.ftgcar.dto.VehicleDto;
import com.ftgcar.exception.NotFoundException;
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
    public List<VehicleDto> addVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleService.addVehicle(vehicleDto);
        return findAllVehicles();
    }

    @GetMapping("/findAll")
    public List<VehicleDto> findAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/find/{id}")
    public VehicleDto findVehicleById(@PathVariable long id) throws NotFoundException {
        return vehicleService.findVehicleById(id);
    }

    @DeleteMapping("/delete/{numberplate}")
    public void deleteVehicleByNumberplate(@PathVariable String numberplate) {
        vehicleService.deleteVehicleByNumberplate(numberplate);
    }

}
