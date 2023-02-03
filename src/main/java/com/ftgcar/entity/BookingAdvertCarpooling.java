package com.ftgcar.entity;

import javax.persistence.*;

@Entity
@Table(name = "booking_advert_carpooling")
public class BookingAdvertCarpooling {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_advert_carpooling", nullable = false)
    private AdvertCarpooling idAdvertCarpooling;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee idEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdvertCarpooling getIdAdvertCarpooling() {
        return idAdvertCarpooling;
    }

    public void setIdAdvertCarpooling(AdvertCarpooling idAdvertCarpooling) {
        this.idAdvertCarpooling = idAdvertCarpooling;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

}