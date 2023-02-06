package com.ftgcar.dao;

import com.ftgcar.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Page<Vehicle> findByCategory(@Param("category") String category, Pageable pageable);

}