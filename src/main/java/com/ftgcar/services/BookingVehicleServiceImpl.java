package com.ftgcar.services;

import com.ftgcar.dao.BookingVehicleRepository;
import com.ftgcar.dto.BookingVehicleDto;
import com.ftgcar.mapper.BookingVehicleMapper;
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
    public List<BookingVehicleDto> findAllBookings() {
        return bookingVehicleRepository
                .findAll()
                .stream()
                .map(bookingVehicleMapper::toDto).toList();
    }
}
