package com.ftgcar.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advert_carpooling")
public class AdvertCarpooling {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "departure", nullable = false)
    private Instant departure;

    @Column(name = "departure_adress", nullable = false, length = 50)
    private String departureAdress;

    @Column(name = "arrival_adress", nullable = false, length = 50)
    private String arrivalAdress;

    @Column(name = "seat_available", nullable = false)
    private Short seatAvailable;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "advertCarpooling", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BookingAdvertCarpooling> bookingAdvertCarpoolings = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDeparture() {
        return departure;
    }

    public void setDeparture(Instant departure) {
        this.departure = departure;
    }

    public String getDepartureAdress() {
        return departureAdress;
    }

    public void setDepartureAdress(String departureAdress) {
        this.departureAdress = departureAdress;
    }

    public String getArrivalAdress() {
        return arrivalAdress;
    }

    public void setArrivalAdress(String arrivalAdress) {
        this.arrivalAdress = arrivalAdress;
    }

    public Short getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(Short seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee idEmployee) {
        this.employee = idEmployee;
    }

    public void addBookingAdvertCarpooling(BookingAdvertCarpooling bookingAdvertCarpooling) {
        bookingAdvertCarpoolings.add(bookingAdvertCarpooling);
        bookingAdvertCarpooling.setAdvertCarpooling(this);
    }

    public void removeBookingAdvertCarpooling(BookingAdvertCarpooling bookingAdvertCarpooling) {
        bookingAdvertCarpoolings.remove(bookingAdvertCarpooling);
        bookingAdvertCarpooling.setAdvertCarpooling(null);
    }

}