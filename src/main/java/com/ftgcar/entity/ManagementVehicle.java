package com.ftgcar.entity;

import javax.persistence.*;

@Entity
@Table(name = "management_vehicle")
public class ManagementVehicle {
    @EmbeddedId
    private ManagementVehicleId id;

    @MapsId("idVehicle")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Long idVehicle;

    @MapsId("idAdmin")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_admin", nullable = false)
    private Long idAdmin;

    public ManagementVehicleId getId() {
        return id;
    }

    public void setId(ManagementVehicleId id) {
        this.id = id;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

}