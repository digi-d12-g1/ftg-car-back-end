package com.ftgcar.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record AdvertCarpoolingDto(
        Long id,
        LocalDateTime departure,
        String departureAdress,
        String arrivalAdress,
        Short seatAvailable,
        EmployeeDto idEmployee) {

}
