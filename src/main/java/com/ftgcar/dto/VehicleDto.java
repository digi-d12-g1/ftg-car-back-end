package com.ftgcar.dto;

import com.ftgcar.enums.Category;
import com.ftgcar.enums.Status;

public record VehicleDto(
                String picture,
                String numberplate,
                String brand,
                String model,
                Status status,
                Category category,
                Short seatCapacity) {
}
