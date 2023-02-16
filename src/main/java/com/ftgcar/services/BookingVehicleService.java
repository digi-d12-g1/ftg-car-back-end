package com.ftgcar.services;

import com.ftgcar.dto.BookingVehicleDto;

import java.util.List;

public interface BookingVehicleService {

    BookingVehicleDto addBooking(BookingVehicleDto bookingVehicleDto);
    void updateBooking(Long id, BookingVehicleDto bookingVehicleDto);
    void deleteBooking(Long id);
    List<BookingVehicleDto> findAllBookings();
}
