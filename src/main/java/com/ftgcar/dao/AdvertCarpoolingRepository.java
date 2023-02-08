package com.ftgcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftgcar.entity.AdvertCarpooling;

public interface AdvertCarpoolingRepository extends JpaRepository<AdvertCarpooling, Long> {
    
List<AdvertCarpooling> findAllByIdVehicle(Long id);

}
