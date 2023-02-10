package com.ftgcar.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advert_carpooling")
public class AdvertCarpooling {
    @Id
    @Column(name = "id", nullable = false)
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
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee idEmployee;

    @OneToMany(mappedBy = "idAdvertCarpooling", cascade = CascadeType.ALL)
    private List<BookingAdvertCarpooling> bookingAdvertCarpoolings = new ArrayList<>();

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

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public List<BookingAdvertCarpooling> getBookingAdvertCarpoolings() {
        return bookingAdvertCarpoolings;
    }

}