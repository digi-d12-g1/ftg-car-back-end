package com.ftgcar.dto;

import com.ftgcar.entity.AdvertCarpooling;
import com.ftgcar.entity.Employee;

public record BookingAdvertCarpoolingDto(
    Long id,
    AdvertCarpooling idAdvertCarpooling,
    Employee idEmployee
) {
    
}
