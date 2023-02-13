package com.ftgcar.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AdvertCarpoolingDto(
    Long id, 
    LocalDateTime departure,
    String departureAdress,
    String arrivalAdress,
    Short seatAvailable,
    EmployeeDto idEmployee,
    List<BookingAdvertCarpoolingDto> bookingAdvertCarpoolingDtos
) {
    
}
