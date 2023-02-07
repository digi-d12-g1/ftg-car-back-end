package com.ftgcar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ftgcar.entity.Employee;
import com.ftgcar.entity.Vehicle;

import java.time.Instant;

/**
 * A DTO for the {@link com.ftgcar.entity.BookingVehicle} entity
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookingVehicleDto(Instant departure, Instant arrival, Vehicle idVehicle,
                                Employee idEmployee)  {
}