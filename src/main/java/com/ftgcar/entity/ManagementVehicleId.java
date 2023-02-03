package com.ftgcar.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ManagementVehicleId implements Serializable {
    private static final long serialVersionUID = -5124938841442337252L;
    @Column(name = "id_vehicle", nullable = false)
    private Long idVehicle;

    @Column(name = "id_admin", nullable = false)
    private Long idAdmin;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ManagementVehicleId entity = (ManagementVehicleId) o;
        return Objects.equals(this.idAdmin, entity.idAdmin) &&
                Objects.equals(this.idVehicle, entity.idVehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdmin, idVehicle);
    }

}