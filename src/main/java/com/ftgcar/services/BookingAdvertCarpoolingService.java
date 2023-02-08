package com.ftgcar.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftgcar.dao.BookingAdvertCarpoolingRepository;
import com.ftgcar.entity.AdvertCarpooling;
import com.ftgcar.entity.BookingAdvertCarpooling;
import com.ftgcar.entity.Vehicle;

@Service
@Transactional
public class BookingAdvertCarpoolingService {

    private final BookingAdvertCarpoolingRepository bookingAdvertCarpoolingRepository;
    private final AdvertCarpoolingService advertCarpoolingService;

    public BookingAdvertCarpoolingService(BookingAdvertCarpoolingRepository bookingAdvertCarpoolingRepository,
            AdvertCarpoolingService advertCarpoolingService) {
        this.bookingAdvertCarpoolingRepository = bookingAdvertCarpoolingRepository;
        this.advertCarpoolingService = advertCarpoolingService;
    }

    public void deleteAllByIdAdvert(List<Long> idsAdvertCarpooling) {

        List<BookingAdvertCarpooling> listOfBookingToDelete = new ArrayList<>();

        for (Long id : idsAdvertCarpooling) {
            List<BookingAdvertCarpooling> listToDelete = bookingAdvertCarpoolingRepository.findAllByIdAdvertCarpooling(id);
            for (BookingAdvertCarpooling toDelete : listToDelete) {
                listToDelete.add(toDelete);
            }
        }
        
        bookingAdvertCarpoolingRepository.deleteAll(listOfBookingToDelete);
    }

}
