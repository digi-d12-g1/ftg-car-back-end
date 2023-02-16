package com.ftgcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftgcar.dto.BookingAdvertCarpoolingDto;
import com.ftgcar.exception.NoMoreSeatException;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.services.BookingAdvertCarpoolingService;

@CrossOrigin
@RestController
@RequestMapping("/api/booking-advert-carpoolings/")
public class BookingAdvertCarpoolingController {

    private final BookingAdvertCarpoolingService bookingAdvertCarpoolingService;

    @Autowired
    public BookingAdvertCarpoolingController(BookingAdvertCarpoolingService bookingAdvertCarpoolingService) {
        this.bookingAdvertCarpoolingService = bookingAdvertCarpoolingService;
    }

    @GetMapping("/findAll/{idEmployee}")
    List<BookingAdvertCarpoolingDto> findAllEmployeesBookingByIdEmployee(@PathVariable Long idEmployee)
            throws NotFoundException {
        return bookingAdvertCarpoolingService.findAllEmployeesBookingByIdEmployee(idEmployee);
    }

    @PostMapping("/book")
    BookingAdvertCarpoolingDto createBooking(@RequestBody BookingAdvertCarpoolingDto bookingAdvertCarpoolingDto)
            throws NotFoundException, NoMoreSeatException {
        return bookingAdvertCarpoolingService.createBooking(bookingAdvertCarpoolingDto);
    }

    @DeleteMapping("/withdraw/{idBookingAdvertCarpooling}")
    void deleteBooking(@PathVariable Long idBookingAdvertCarpooling) throws NotFoundException {
        bookingAdvertCarpoolingService.withdrawBooking(idBookingAdvertCarpooling);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(NoMoreSeatException.class)
    public ResponseEntity<String> handleNoMoreSeatException(NoMoreSeatException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

}
