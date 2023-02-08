package com.ftgcar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookingVehicleDto(LocalDateTime departure, LocalDateTime arrival, Long idVehicle,
                                Long idEmployee)  {
}