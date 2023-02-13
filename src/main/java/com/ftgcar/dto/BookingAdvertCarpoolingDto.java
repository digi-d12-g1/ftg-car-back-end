package com.ftgcar.dto;

public record BookingAdvertCarpoolingDto(
    Long id,
    AdvertCarpoolingDto advertCarpoolingDto,
    EmployeeDto employeeDto
) {
    
}
