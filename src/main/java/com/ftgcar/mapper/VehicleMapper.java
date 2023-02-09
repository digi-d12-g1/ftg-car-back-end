package com.ftgcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ftgcar.dto.VehicleDto;
import com.ftgcar.entity.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);

    VehicleDto vehicleToVehicleDto(Vehicle vehicle);

    Vehicle updateVehicle(VehicleDto vehicleDto, @MappingTarget Vehicle vehicleToUpdate);
}
