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
    private Vehicle idVehicle;

    @MapsId("idAdmin")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin idAdmin;

    public ManagementVehicleId getId() {
        return id;
    }

    public void setId(ManagementVehicleId id) {
        this.id = id;
    }

    public Vehicle getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Vehicle idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Admin getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

}