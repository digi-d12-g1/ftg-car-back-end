package com.ftgcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftgcar.entity.BookingAdvertCarpooling;

@Repository
public interface BookingAdvertCarpoolingRepository extends JpaRepository<BookingAdvertCarpooling, Long>{
    
    List<BookingAdvertCarpooling> findAllByIdEmployee(Long id);

    List<BookingAdvertCarpooling> findAllByIdAdvertCarpooling(Long id);

    
}
