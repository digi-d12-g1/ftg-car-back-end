package com.ftgcar.dto;

public record VehicleDto(
                String picture,
                String numberplate,
                String brand,
                String model,
                String status,
                String category,
                Short seatCapacity) {
}
