package com.ftgcar.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "booking_vehicle")
public class BookingVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "departure", nullable = false)
    private Instant departure;

    @Column(name = "arrival")
    private Instant arrival;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Vehicle idVehicle;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee idEmployee;

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

    public Instant getArrival() {
        return arrival;
    }

    public void setArrival(Instant arrival) {
        this.arrival = arrival;
    }

    public Vehicle getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Vehicle idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

}