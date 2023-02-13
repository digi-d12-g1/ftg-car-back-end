package com.ftgcar.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.AdvertCarpoolingRepository;
import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.mapper.AdvertCarpoolingMapper;

@Service
@Transactional
public class AdvertCarpoolingService {

    private final AdvertCarpoolingRepository advertCarpoolingRepository;
    private final AdvertCarpoolingMapper advertCarpoolingMapper;

    public AdvertCarpoolingService(AdvertCarpoolingRepository advertCarpoolingRepository, AdvertCarpoolingMapper advertCarpoolingMapper) {
        this.advertCarpoolingRepository = advertCarpoolingRepository;
        this.advertCarpoolingMapper = advertCarpoolingMapper;
    }

    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingsWithSeatAvailable() {
        Short zero = 0;
        return advertCarpoolingRepository
        .findBySeatAvailableGreaterThan(zero)
        .stream()
        .map(advertCarpoolingMapper::advertCarpoolingToAdvertCarpoolingDto)
        .toList();
    }
    
}
