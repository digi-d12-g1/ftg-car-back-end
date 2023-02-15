package com.ftgcar.mapper;

import org.mapstruct.Mapper;

import com.ftgcar.dto.BookingAdvertCarpoolingDto;
import com.ftgcar.entity.BookingAdvertCarpooling;

@Mapper(componentModel = "spring", uses = {AdvertCarpoolingMapper.class, EmployeeMapper.class})
public interface BookingAdvertCarpoolingMapper {

    BookingAdvertCarpooling bookingAdvertCarpoolingDtoToBookingCarpooling(BookingAdvertCarpoolingDto bookingAdvertCarpoolingDto);

    BookingAdvertCarpoolingDto bookingAdvertCarpoolingToBookingAdvertCarpoolingDto(BookingAdvertCarpooling bookingAdvertCarpooling);
    
}
