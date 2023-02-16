package com.ftgcar.dto;

import com.ftgcar.entity.Employee;
import com.ftgcar.entity.Vehicle;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.ftgcar.entity.BookingVehicle} entity
 */
public record BookingVehicleDto(
    LocalDateTime departure,
    LocalDateTime arrival, 
    Vehicle idVehicle,
    Employee idEmployee)  {
}