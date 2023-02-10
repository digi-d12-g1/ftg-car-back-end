package com.ftgcar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.ftgcar.dao.VehicleRepository;
import com.ftgcar.dto.VehicleDto;
import com.ftgcar.entity.Vehicle;
import com.ftgcar.exception.AlreadyExistsException;
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

    public VehicleDto addVehicle(VehicleDto vehicleDto) throws AlreadyExistsException {
        checkIfVehicleAlreadyExistsForAdd(vehicleDto);
        vehicleRepository.save(vehicleMapper.vehicleDtoToVehicle(vehicleDto));
        return vehicleDto;
    }

    private void checkIfVehicleAlreadyExistsForAdd(VehicleDto vehicleDto) throws AlreadyExistsException {
        Optional<Vehicle> existingVehicleById = vehicleRepository.findById(vehicleDto.id());
        if (existingVehicleById.isPresent()) {
            throw new AlreadyExistsException(String.format("Le véhicule à l'id %s existe déjà.", vehicleDto.id()));
        }
        Optional<Vehicle> existingVehicleByNumberplate = vehicleRepository.findByNumberplate(vehicleDto.numberplate());
        if (existingVehicleByNumberplate.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("Le véhicule avec l'immatriculation %s existe déjà.", vehicleDto.numberplate()));
        }
    }

    public List<VehicleDto> findAllVehicles() {
        return vehicleRepository
                .findAll()
                .stream()
                .map(vehicleMapper::vehicleToVehicleDto).toList();
    }

    public VehicleDto findVehicleById(long id) throws NotFoundException {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isEmpty()) {
            throw new NotFoundException("Le véhicule demandé n'existe pas.");
        }
        return vehicleMapper.vehicleToVehicleDto(existingVehicle.get());
    }

    public void deleteVehicleById(Long id) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            vehicleRepository.deleteById(id);
        }
    }

    public VehicleDto updateVehicle(VehicleDto vehicleDto) throws AlreadyExistsException {
        Optional<Vehicle> vehicleToUpdate = vehicleRepository.findById(vehicleDto.id());
        if (vehicleToUpdate.isPresent()) {
            Optional<Vehicle> vehicleExistingWithNumberplate = vehicleRepository
                    .findByNumberplate(vehicleDto.numberplate());
            if (vehicleExistingWithNumberplate.isPresent()
                    && !vehicleExistingWithNumberplate.get().getId().equals(vehicleDto.id())) {
                throw new AlreadyExistsException(
                        String.format("L'immatriculation %s existe déjà.", vehicleDto.numberplate()));
            }
            return vehicleMapper.vehicleToVehicleDto(vehicleMapper.updateVehicle(vehicleDto, vehicleToUpdate.get()));
        }
        return addVehicle(vehicleDto);
    }
}
