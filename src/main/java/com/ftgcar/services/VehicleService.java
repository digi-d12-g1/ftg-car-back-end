package com.ftgcar.services;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.VehicleRepository;
import com.ftgcar.dto.VehicleDto;
import com.ftgcar.mapper.VehicleMapper;

@Service
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

}
