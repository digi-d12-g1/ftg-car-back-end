package com.ftgcar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record VehicleDto(String picture, String numberplate, String brand, String model, String status, String category,
        Short seatCapacity) {

}
