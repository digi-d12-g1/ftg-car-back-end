package com.ftgcar.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.BookingAdvertCarpoolingRepository;
import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.dto.BookingAdvertCarpoolingDto;
import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.mapper.BookingAdvertCarpoolingMapper;

@Service
public class BookingAdvertCarpoolingService {

    private final BookingAdvertCarpoolingRepository bookingAdvertCarpoolingRepository;
    private final EmployeeService employeeService;
    private final BookingAdvertCarpoolingMapper bookingAdvertCarpoolingMapper;
    private final AdvertCarpoolingService advertCarpoolingService;

    public BookingAdvertCarpoolingService(BookingAdvertCarpoolingRepository bookingAdvertCarpoolingRepository,
            EmployeeService employeeService, BookingAdvertCarpoolingMapper bookingAdvertCarpoolingMapper, AdvertCarpoolingService advertCarpoolingService) {
        this.bookingAdvertCarpoolingRepository = bookingAdvertCarpoolingRepository;
        this.employeeService = employeeService;
        this.bookingAdvertCarpoolingMapper = bookingAdvertCarpoolingMapper;
        this.advertCarpoolingService = advertCarpoolingService;
    }

    public List<BookingAdvertCarpoolingDto> findAllEmployeesBookingByIdEmployee(Long idEmployee)
            throws NotFoundException {
        EmployeeDto existingEmployee = employeeService.findEmployeeById(idEmployee);
        return bookingAdvertCarpoolingRepository.findAllByIdEmployee(existingEmployee.id())
                .stream()
                .map(bookingAdvertCarpoolingMapper::bookingAdvertCarpoolingToBookingAdvertCarpoolingDto)
                .toList();
    }

    public BookingAdvertCarpoolingDto createBooking(BookingAdvertCarpoolingDto bookingAdvertCarpoolingDto) throws NotFoundException {
        employeeService.findEmployeeById(bookingAdvertCarpoolingDto.employeeDto().id());
        AdvertCarpoolingDto existingAdvertCarpooling = advertCarpoolingService.findAdvertCarpoolingById(bookingAdvertCarpoolingDto.advertCarpoolingDto().id());

        if (existingAdvertCarpooling.seatAvailable() > 0) {
            advertCarpoolingService.bookASeat(existingAdvertCarpooling);
            bookingAdvertCarpoolingRepository.save(bookingAdvertCarpoolingMapper.bookingAdvertCarpoolingDtoToBookingCarpooling(bookingAdvertCarpoolingDto));
        }
        
        return bookingAdvertCarpoolingDto;
    }

}
