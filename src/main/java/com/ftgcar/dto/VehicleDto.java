package com.ftgcar.dto;

public record VehicleDto(
                Long id,
                String picture,
                String numberplate,
                String brand,
                String model,
                String vehicleStatus,
                String category,
                Short seatCapacity) {
}
