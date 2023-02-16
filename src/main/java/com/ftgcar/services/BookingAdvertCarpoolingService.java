package com.ftgcar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.BookingAdvertCarpoolingRepository;
import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.dto.BookingAdvertCarpoolingDto;
import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.entity.BookingAdvertCarpooling;
import com.ftgcar.exception.AlreadyExistsException;
import com.ftgcar.exception.BookingImpossibleException;
import com.ftgcar.exception.NoMoreSeatException;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.mapper.BookingAdvertCarpoolingMapper;

@Service
public class BookingAdvertCarpoolingService {

    private final BookingAdvertCarpoolingRepository bookingAdvertCarpoolingRepository;
    private final EmployeeService employeeService;
    private final BookingAdvertCarpoolingMapper bookingAdvertCarpoolingMapper;
    private final AdvertCarpoolingService advertCarpoolingService;

    public BookingAdvertCarpoolingService(BookingAdvertCarpoolingRepository bookingAdvertCarpoolingRepository,
            EmployeeService employeeService, BookingAdvertCarpoolingMapper bookingAdvertCarpoolingMapper,
            AdvertCarpoolingService advertCarpoolingService) {
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

    public BookingAdvertCarpoolingDto createBooking(BookingAdvertCarpoolingDto bookingAdvertCarpoolingDto)
            throws NotFoundException, NoMoreSeatException, AlreadyExistsException, BookingImpossibleException {
        
        employeeService.findEmployeeById(bookingAdvertCarpoolingDto.idEmployee().getId());

        if (bookingAdvertCarpoolingDto.idAdvertCarpooling().getIdEmployee().getId().equals(bookingAdvertCarpoolingDto
                .idEmployee().getId())) {
            throw new BookingImpossibleException(
                    "Impossible de réserver une place dans un covoiturage créé par l'utilisateur!");
        }

        List<BookingAdvertCarpooling> existingBooking = bookingAdvertCarpoolingRepository
                .findAllByIdEmployeeAndIdAdvertCarpooling(
                        bookingAdvertCarpoolingDto.idEmployee().getId(),
                        bookingAdvertCarpoolingDto.idAdvertCarpooling().getId());
        if (!existingBooking.isEmpty()) {
            throw new AlreadyExistsException("Une réservation existe déjà sur ce covoiturage.");
        }

        AdvertCarpoolingDto existingAdvertCarpooling = advertCarpoolingService
                .findAdvertCarpoolingById(bookingAdvertCarpoolingDto.idAdvertCarpooling().getId());

        if (existingAdvertCarpooling.seatAvailable() > 0) {
            advertCarpoolingService.bookASeat(existingAdvertCarpooling);
            bookingAdvertCarpoolingRepository.save(bookingAdvertCarpoolingMapper
                    .bookingAdvertCarpoolingDtoToBookingCarpooling(bookingAdvertCarpoolingDto));
        } else {
            throw new NoMoreSeatException("Il n'y a plus de sièges disponibles pour ce covoiturage.");
        }
        return bookingAdvertCarpoolingDto;
    }

    public void withdrawBooking(Long idBookingAdvertCarpooling) throws NotFoundException {
        Optional<BookingAdvertCarpooling> bookingToDelete = bookingAdvertCarpoolingRepository
                .findById(idBookingAdvertCarpooling);
        if (bookingToDelete.isEmpty()) {
            throw new NotFoundException("La réservation de covoiturage n'existe pas.");
        }
        advertCarpoolingService.freeUpASeat(bookingAdvertCarpoolingMapper
                .bookingAdvertCarpoolingToBookingAdvertCarpoolingDto(bookingToDelete.get()));
        bookingAdvertCarpoolingRepository.deleteById(idBookingAdvertCarpooling);
    }

}
