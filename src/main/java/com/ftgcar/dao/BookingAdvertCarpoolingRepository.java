package com.ftgcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftgcar.entity.BookingAdvertCarpooling;

public interface BookingAdvertCarpoolingRepository extends JpaRepository<BookingAdvertCarpooling, Long>{
    
    @Query(value = "SELECT * FROM public.booking_advert_carpooling booking WHERE booking.id_employee = (:employeeID)", nativeQuery = true)
    List<BookingAdvertCarpooling> findAllByIdEmployee(@Param("employeeID") Long idEmployee);

}
