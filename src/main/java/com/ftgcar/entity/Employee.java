package com.ftgcar.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 25)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "idEmployee", cascade = CascadeType.ALL)
    private final List<AdvertCarpooling> advertCarpoolings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<BookingVehicle> bookingVehicles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AdvertCarpooling> getAdvertCarpoolings() {
        return advertCarpoolings;
    }

    public List<BookingVehicle> getBookingVehicles() {
        return bookingVehicles;
    }

}