package com.ftgcar.mapper;

import com.ftgcar.dto.BookingVehicleDto;
import com.ftgcar.entity.BookingVehicle;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = VehicleMapper.class)
public interface BookingVehicleMapper {

    BookingVehicle toEntity(BookingVehicleDto bookingVehicleDto);

    BookingVehicleDto toDto(BookingVehicle bookingVehicle);

}