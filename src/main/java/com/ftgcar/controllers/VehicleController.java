package com.ftgcar.controllers;

import com.ftgcar.dto.VehicleDto;
import com.ftgcar.exception.AlreadyExistsException;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<VehicleDto> addVehicle(@RequestBody VehicleDto vehicleDto) throws AlreadyExistsException {
        return vehicleService.addVehicle(vehicleDto);
    }

    @GetMapping("/findAll")
    public List<VehicleDto> findAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/find/{id}")
    public VehicleDto findVehicleById(@PathVariable long id) throws NotFoundException {
        return vehicleService.findVehicleById(id);
    }

    @DeleteMapping("/delete/{id}")
    // @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteVehicleById(@PathVariable long id) throws NotFoundException {
        vehicleService.deleteVehicleById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicleDto) throws AlreadyExistsException, NotFoundException {
        return vehicleService.updateVehicle(vehicleDto);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
