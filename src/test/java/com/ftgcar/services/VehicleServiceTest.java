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

    @Nested
    class AddVehicleTest {

        VehicleDto vehicleDtoToAdd = new VehicleDto(1L, "picture", "numberplate", "brand", "model", "available",
                    "category", (short) 2);
        Vehicle vehicleToAdd = new Vehicle(1L, "picture", "numberplate", "brand", "model", "available", "category", (short) 2);
        
        @Test
        void addVehicleTest_withVehicleDto_savesVehicleAndReturnsVehicleDto() throws AlreadyExistsException {
            // given
            when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());
            when(vehicleRepository.findByNumberplate("numberplate")).thenReturn(Optional.empty());
            when(vehicleMapper.vehicleDtoToVehicle(vehicleDtoToAdd)).thenReturn(vehicleToAdd);
            when(vehicleRepository.save(vehicleToAdd)).thenReturn(vehicleToAdd);
            when(vehicleMapper.vehicleToVehicleDto(vehicleToAdd)).thenReturn(vehicleDtoToAdd);

            // when
            VehicleDto returnedVehicle = vehicleService.addVehicle(vehicleDtoToAdd);

            // then
            assertSame(returnedVehicle, vehicleDtoToAdd);
        }

        @Test
        void addVehicleTest_withVehicleDtoNumberplateAlreadyExisting_throwsAlreadyExistsException() {
            // given
            when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());
            when(vehicleRepository.findByNumberplate("numberplate")).thenReturn(Optional.of(vehicleToAdd));
           
            // when/then
            assertThrows(AlreadyExistsException.class, () -> {vehicleService.addVehicle(vehicleDtoToAdd);}, "Le véhicule avec l'immatriculation numberplate existe déjà.");
        }
    }
}
