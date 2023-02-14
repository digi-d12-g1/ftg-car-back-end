package com.ftgcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ftgcar.entity.AdvertCarpooling;

@RepositoryRestController
public interface AdvertCarpoolingRepository extends JpaRepository<AdvertCarpooling, Long> {

    List<AdvertCarpooling> findAllBySeatAvailableGreaterThan(Short zero);

    @Query(value = "SELECT * FROM public.advert_carpooling advert WHERE advert.id_employee = (:employeeID)", nativeQuery = true)
    List<AdvertCarpooling> findAllByIdEmployeId(@Param("employeeID") Long id);

}
