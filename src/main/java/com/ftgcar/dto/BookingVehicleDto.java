package com.ftgcar.dto;

import java.time.LocalDateTime;

public record BookingVehicleDto(
        Long id,
        LocalDateTime departure,
        LocalDateTime arrival) {
}