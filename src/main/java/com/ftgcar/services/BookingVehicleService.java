package com.ftgcar.services;

import com.ftgcar.dto.BookingVehicleDto;

import java.util.List;

public interface BookingVehicleService {

    BookingVehicleDto addBooking(BookingVehicleDto bookingVehicleDto);
    List<BookingVehicleDto> findAllBookings();
}
