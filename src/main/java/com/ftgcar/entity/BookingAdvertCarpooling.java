package com.ftgcar.entity;

import javax.persistence.*;

@Entity
@Table(name = "booking_advert_carpooling")
public class BookingAdvertCarpooling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_advert_carpooling")
    private AdvertCarpooling advertCarpooling;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdvertCarpooling getAdvertCarpooling() {
        return advertCarpooling;
    }

    public void setAdvertCarpooling(AdvertCarpooling advertCarpooling) {
        this.advertCarpooling = advertCarpooling;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}