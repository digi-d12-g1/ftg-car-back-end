package com.ftgcar.controllers;

import com.ftgcar.dto.BookingVehicleDto;
import com.ftgcar.services.BookingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
public class BookingVehicleController {

    private final BookingVehicleService bookingVehicleService;

    @Autowired
    public BookingVehicleController(BookingVehicleService bookingVehicleService) {
        this.bookingVehicleService = bookingVehicleService;
    }

    @GetMapping("/findAll")
    public List<BookingVehicleDto> findAllBookings() {
        return bookingVehicleService.findAllBookings();
    }

    @PostMapping
    public BookingVehicleDto addBooking(@RequestBody BookingVehicleDto bookingVehicleDto) {
        return this.bookingVehicleService.addBooking(bookingVehicleDto);
    }
}
