package com.ftgcar.dao;

import com.ftgcar.entity.BookingVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bookingVehicle", path = "bookings")
public interface BookingVehicleRepository extends JpaRepository<BookingVehicle, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM booking_vehicle b WHERE b.id_employee = :employeeId")
    List<BookingVehicle>findBookingVehicleByIdEmployee(@Param("employeeId") Long employeeId);
}