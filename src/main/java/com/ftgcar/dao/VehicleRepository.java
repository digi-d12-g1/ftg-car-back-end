package com.ftgcar.dao;

import com.ftgcar.entity.Vehicle;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByNumberplate(String numberplate);

    Page<Vehicle> findByCategory(@Param("category") String category, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM vehicle v WHERE " +
                    "v.id NOT IN (SELECT id_vehicle FROM booking_vehicle WHERE " +
                    "(departure <= :arrival AND arrival >= :departure))")
    List<Vehicle> findByBookingDate(@Param("departure") Date departure, @Param("arrival") Date arrival);
}