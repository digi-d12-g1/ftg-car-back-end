package com.ftgcar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.services.AdvertCarpoolingService;

@CrossOrigin
@RestController
@RequestMapping("/api/advert-carpoolings")
public class AdvertCarpoolingController {

    private final AdvertCarpoolingService advertCarpoolingService;

    @Autowired
    public AdvertCarpoolingController(AdvertCarpoolingService advertCarpoolingService) {
        this.advertCarpoolingService = advertCarpoolingService;
    }

    @GetMapping("/findAllAvailable")
    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingsWithSeatAvailable() {
        return advertCarpoolingService.findAllAdvertCarpoolingsWithSeatAvailable();
    }


}
