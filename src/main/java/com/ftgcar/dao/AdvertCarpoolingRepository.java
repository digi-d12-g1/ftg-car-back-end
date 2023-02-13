package com.ftgcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ftgcar.entity.AdvertCarpooling;

@RepositoryRestController
public interface AdvertCarpoolingRepository extends JpaRepository<AdvertCarpooling, Long> {

    List<AdvertCarpooling> findBySeatAvailableGreaterThan(Short zero);

}
