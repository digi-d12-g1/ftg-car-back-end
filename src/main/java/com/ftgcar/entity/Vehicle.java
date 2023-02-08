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
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture", nullable = false, length = 300)
    private String picture;

    @Column(name = "numberplate", nullable = false, length = 10)
    private String numberplate;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "category", nullable = false, length = 25)
    private String category;

    @Column(name = "seat_capacity", nullable = false)
    private Short seatCapacity;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BookingVehicle> bookingVehicles = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AdvertCarpooling> advertCarpoolings = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(String picture, String brand, String model, String status, String category, Short seatCapacity) {
        this.picture = picture;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.category = category;
        this.seatCapacity = seatCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Short getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Short seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public void addBookingVehicle(BookingVehicle bookingVehicle) {
        bookingVehicles.add(bookingVehicle);
        bookingVehicle.setVehicle(this);
    }

    public void removeBookingVehicle(BookingVehicle bookingVehicle) {
        bookingVehicles.remove(bookingVehicle);
        bookingVehicle.setVehicle(null);
    }

    public void addAdvertCarpooling(AdvertCarpooling advertCarpooling) {
        advertCarpoolings.add(advertCarpooling);
        advertCarpooling.setVehicle(this);
    }

    public void removeAdvertCarpooling(AdvertCarpooling advertCarpooling) {
        advertCarpoolings.remove(advertCarpooling);
        advertCarpooling.setVehicle(null);
    }

}