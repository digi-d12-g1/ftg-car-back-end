package com.ftgcar.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftgcar.dao.AdvertCarpoolingRepository;
import com.ftgcar.entity.AdvertCarpooling;
import com.ftgcar.entity.Vehicle;

@Service
@Transactional
public class AdvertCarpoolingService {
    

    private final AdvertCarpoolingRepository advertCarpoolingRepository;
    private final BookingAdvertCarpoolingService bookingAdvertCarpoolingService;

    public AdvertCarpoolingService(AdvertCarpoolingRepository advertCarpoolingRepository, BookingAdvertCarpoolingService bookingAdvertCarpoolingService) {
        this.advertCarpoolingRepository = advertCarpoolingRepository;
        this.bookingAdvertCarpoolingService = bookingAdvertCarpoolingService;
    }


    public void deleteAdvertCarpoolingByIdVehicle(Long idVehicle) {
        List<AdvertCarpooling> listToDelete = advertCarpoolingRepository.findAllByIdVehicle(idVehicle);

        List<Long> idsAdvertCarpooling = new ArrayList<>();
        for (AdvertCarpooling advert : listToDelete) {
            idsAdvertCarpooling.add(advert.getId());
        }
        bookingAdvertCarpoolingService.deleteAllByIdAdvert(idsAdvertCarpooling);
        advertCarpoolingRepository.deleteAll(listToDelete);
    }
    
}
