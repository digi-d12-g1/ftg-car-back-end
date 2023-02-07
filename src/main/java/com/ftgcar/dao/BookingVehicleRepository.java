package com.ftgcar.dao;

import com.ftgcar.entity.BookingVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookingVehicle", path = "bookings")
public interface BookingVehicleRepository extends JpaRepository<BookingVehicle, Long> {
}