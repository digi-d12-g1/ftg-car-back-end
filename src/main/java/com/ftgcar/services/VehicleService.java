package com.ftgcar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ftgcar.dao.VehicleRepository;
import com.ftgcar.dto.VehicleDto;
import com.ftgcar.entity.Vehicle;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.mapper.VehicleMapper;

@Service
@Transactional
public class VehicleService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleMapper vehicleMapper, VehicleRepository vehicleRepository) {
        this.vehicleMapper = vehicleMapper;
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleDto addVehicle(VehicleDto vehicleDto) {
        vehicleRepository.save(vehicleMapper.vehicleDtoToVehicle(vehicleDto));
        return vehicleDto;
    }

    public List<VehicleDto> findAllVehicles() {

        return vehicleRepository.findAll().stream().map(vehicleMapper::vehicleToVehicleDto).toList();
    }

    public VehicleDto findVehicleById(long id) throws NotFoundException {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isEmpty()) {
            throw new NotFoundException("Sauf erreur de ma part, le véhicule demandé n'existe pas.");
        }
        return vehicleMapper.vehicleToVehicleDto(existingVehicle.get());
    }

    public void deleteVehicleById(long id) {
        vehicleRepository.deleteById(id);
    }

}