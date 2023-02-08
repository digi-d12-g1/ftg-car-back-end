package com.ftgcar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ftgcar.dao.VehicleRepository;
import com.ftgcar.dto.VehicleDto;
import com.ftgcar.entity.Vehicle;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.mapper.VehicleMapper;

@Service
public class VehicleService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final AdvertCarpoolingService advertCarpoolingService;
    private final BookingAdvertCarpoolingService bookingAdvertCarpoolingService;

    public VehicleService(AdvertCarpoolingService advertCarpoolingService, VehicleMapper vehicleMapper, VehicleRepository vehicleRepository, BookingAdvertCarpoolingService bookingAdvertCarpoolingService) {
        this.advertCarpoolingService = advertCarpoolingService;
        this.vehicleMapper = vehicleMapper;
        this.vehicleRepository = vehicleRepository;
        this.bookingAdvertCarpoolingService = bookingAdvertCarpoolingService;
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

    public void deleteVehicleByNumberplate(String numberplate) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findByNumberplate(numberplate);
        if (existingVehicle.isPresent()) {
            advertCarpoolingService.deleteAdvertCarpoolingByIdVehicle(existingVehicle.get().getId());
        }
        vehicleRepository.deleteByNumberplate(numberplate);
    }

}
