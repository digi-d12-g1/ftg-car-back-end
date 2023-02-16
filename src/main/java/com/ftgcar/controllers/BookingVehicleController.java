package com.ftgcar.controllers;

import com.ftgcar.dto.BookingVehicleDto;
import com.ftgcar.services.BookingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Booking vehicle controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
public class BookingVehicleController {

    private final BookingVehicleService bookingVehicleService;

    /**
     * Instantiates a new Booking vehicle controller.
     *
     * @param bookingVehicleService the booking vehicle service
     */
    @Autowired
    public BookingVehicleController(BookingVehicleService bookingVehicleService) {
        this.bookingVehicleService = bookingVehicleService;
    }

    @GetMapping("/findAll")
    public List<BookingVehicleDto> findAllBookings() {
        return bookingVehicleService.findAllBookings();
    }

    @GetMapping("/findByEmployee/{employeeId}")
    public List<BookingVehicleDto> findAllByEmployee(@PathVariable Long employeeId) {
        return this.bookingVehicleService.findAllByEmployeeId(employeeId);
    }

    @PostMapping
    public BookingVehicleDto addBooking(@RequestBody BookingVehicleDto bookingVehicleDto) {
        return this.bookingVehicleService.addBooking(bookingVehicleDto);
    }

    @PutMapping("/{id}")
    public void updateBooking(@PathVariable Long id, @RequestBody BookingVehicleDto bookingVehicleDto) {
        this.bookingVehicleService.updateBooking(id, bookingVehicleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        this.bookingVehicleService.deleteBooking(id);
    }
}
