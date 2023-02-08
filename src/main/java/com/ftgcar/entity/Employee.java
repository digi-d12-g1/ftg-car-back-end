package com.ftgcar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, length = 25)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BookingVehicle> bookingVehicles = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BookingAdvertCarpooling> bookingAdvertCarpoolings = new ArrayList<>();

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

    public void addBookingAdvertCarpooling(BookingAdvertCarpooling bookingAdvertCarpooling) {
        bookingAdvertCarpoolings.add(bookingAdvertCarpooling);
        bookingAdvertCarpooling.setEmployee(this);
    }

    public void removeBookingAdvertCarpooling(BookingAdvertCarpooling bookingAdvertCarpooling) {
        bookingAdvertCarpoolings.remove(bookingAdvertCarpooling);
        bookingAdvertCarpooling.setEmployee(null);
    }

    public void addBookingVehicle(BookingVehicle bookingVehicle) {
        bookingVehicles.add(bookingVehicle);
        bookingVehicle.setEmployee(this);
    }

    public void removeBookingVehicle(BookingVehicle bookingVehicle) {
        bookingVehicles.remove(bookingVehicle);
        bookingVehicle.setEmployee(null);
    } 
}