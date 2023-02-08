package com.ftgcar.dto;

import com.ftgcar.entity.Employee;
import com.ftgcar.entity.Vehicle;
import java.time.LocalDateTime;

public record BookingVehicleDto(
                LocalDateTime departure,
                LocalDateTime arrival,
                Vehicle vehicle,
                Employee employee) {
}