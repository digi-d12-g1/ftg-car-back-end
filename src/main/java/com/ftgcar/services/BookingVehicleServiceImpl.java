package com.ftgcar.services;

import com.ftgcar.dao.BookingVehicleRepository;
import com.ftgcar.dto.BookingVehicleDto;
import com.ftgcar.exception.ResourceNotFound;
import com.ftgcar.mapper.BookingVehicleMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookingVehicleServiceImpl implements BookingVehicleService {

    private final BookingVehicleRepository bookingVehicleRepository;
    private final BookingVehicleMapper bookingVehicleMapper;

    public BookingVehicleServiceImpl(BookingVehicleRepository bookingVehicleRepository, BookingVehicleMapper bookingVehicleMapper) {
        this.bookingVehicleRepository = bookingVehicleRepository;
        this.bookingVehicleMapper = bookingVehicleMapper;
    }

    @Override
    @Transactional
    public BookingVehicleDto addBooking(BookingVehicleDto bookingVehicleDto) {
        this.bookingVehicleRepository.save(bookingVehicleMapper.toEntity(bookingVehicleDto));
        return bookingVehicleDto;
    }

    @Override
    public void updateBooking(Long id, BookingVehicleDto bookingVehicleDto) {
        this.bookingVehicleRepository.findById(id).map(booking -> {
            return bookingVehicleRepository.save(bookingVehicleMapper.toEntity(bookingVehicleDto));
        }).orElseThrow(() -> new NullPointerException(String.format("Error while updating booking for id %d", id)));
    }

    @Override
    public void deleteBooking(Long id) {
        try {
            this.bookingVehicleRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFound(String.format("Product with id %s not found", id));
        }
    }

    @Override
    public List<BookingVehicleDto> findAllBookings() {
        return bookingVehicleRepository
                .findAll()
                .stream()
                .map(bookingVehicleMapper::toDto).toList();
    }
}
