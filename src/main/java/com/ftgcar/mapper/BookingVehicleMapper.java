package com.ftgcar.mapper;

import com.ftgcar.dto.BookingVehicleDto;
import com.ftgcar.entity.BookingVehicle;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingVehicleMapper {
    BookingVehicle toEntity(BookingVehicleDto bookingVehicleDto);

    BookingVehicleDto toDto(BookingVehicle bookingVehicle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookingVehicle partialUpdate(BookingVehicleDto bookingVehicleDto, @MappingTarget BookingVehicle bookingVehicle);
}