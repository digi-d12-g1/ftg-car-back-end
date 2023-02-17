package com.ftgcar.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ftgcar.dao.VehicleRepository;
import com.ftgcar.dto.VehicleDto;
import com.ftgcar.entity.Vehicle;
import com.ftgcar.exception.AlreadyExistsException;
import com.ftgcar.mapper.VehicleMapper;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

        @InjectMocks
        private VehicleService vehicleService;

        @Mock
        private VehicleRepository vehicleRepository;

        @Mock
        private VehicleMapper vehicleMapper;

        VehicleDto vehicleDto = new VehicleDto(1L, "picture", "numberplate", "brand", "model", "available",
                        "category", (short) 2);
        Vehicle vehicle = new Vehicle();

        @Nested
        class AddVehicleTest {

        @Test
        void addVehicleTest_withVehicleDto_savesVehicleAndReturnsVehicleDto() throws AlreadyExistsException {
        // given
        vehicle.setId(1L);
        vehicle.setPicture("picture");
        vehicle.setNumberplate("numberplate");
        vehicle.setBrand("brand");
        vehicle.setModel("model");
        vehicle.setVehicleStatus("available");
        vehicle.setCategory("category");
        vehicle.setSeatCapacity((short) 2);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());
        when(vehicleRepository.findByNumberplate("numberplate")).thenReturn(Optional.empty());
        when(vehicleMapper.vehicleDtoToVehicle(vehicleDto)).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(vehicleMapper.vehicleToVehicleDto(vehicle)).thenReturn(vehicleDto);

        // when
        VehicleDto returnedVehicle = vehicleService.addVehicle(vehicleDto);

        // then
        assertSame(returnedVehicle, vehicleDto);
        }

        @Test
        void addVehicleTest_withVehicleDtoNumberplateAlreadyExisting_throwsAlreadyExistsException() {
        // given
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());
        when(vehicleRepository.findByNumberplate("numberplate")).thenReturn(Optional.of(vehicle));
        
        // when/then
        assertThrows(AlreadyExistsException.class, () -> {vehicleService.addVehicle(vehicleDto);}, "Le véhicule avec l'immatriculation numberplate existe déjà.");
        }
        }

}
