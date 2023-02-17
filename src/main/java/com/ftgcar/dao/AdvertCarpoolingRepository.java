package com.ftgcar.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftgcar.entity.AdvertCarpooling;

@Repository
public interface AdvertCarpoolingRepository extends JpaRepository<AdvertCarpooling, Long> {

    @Query(value = "SELECT * FROM advert_carpooling advert WHERE departure = :date", nativeQuery = true)
    List<AdvertCarpooling> findAllByDepartureEqual(
            @Param("date") Date date);

    @Query(value = "SELECT * FROM public.advert_carpooling advert WHERE advert.id_employee = (:employeeID)", nativeQuery = true)
    List<AdvertCarpooling> findAllByIdEmployeId(@Param("employeeID") Long idEmployee);

}
