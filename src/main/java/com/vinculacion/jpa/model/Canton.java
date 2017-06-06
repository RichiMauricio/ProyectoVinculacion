package com.vinculacion.jpa.model;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static javax.persistence.AccessType.PROPERTY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mauricio on 19/04/2017.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Canton")
@Access(PROPERTY)
public class Canton implements Serializable{

    private Long cntId;
    private String cntNombre;
    private Double cntArea;
    private String cntDescripcion;
    private Set<Establecimiento> establecimientos;

    public static final int DIMENSION_NOMBRE_CANTON = 40;

    @Transient
    public boolean isNew() {
        return (this.cntId == null);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cntId")
    public Long getCntId() {
        return cntId;
    }

    public void setCntId(Long cntId) {
        this.cntId = cntId;
    }

    @Basic
    @Column(name = "cntNombre", insertable = true, updatable = true, length = DIMENSION_NOMBRE_CANTON)
    public String getCntNombre() {
        return cntNombre;
    }

    public void setCntNombre(String cntNombre) {
        this.cntNombre = cntNombre;
    }

    @Column(name = "cntArea", unique = false, updatable = true)
    public Double getCntArea() {
        return cntArea;
    }

    public void setCntArea(Double cntArea) {
        this.cntArea = cntArea;
    }

    @Column(name = "cntDescripcion", unique = false, nullable = true, updatable = true)

    public String getCntDescripcion() {
        return cntDescripcion;
    }

    public void setCntDescripcion(String cntDescripcion) {
        this.cntDescripcion = cntDescripcion;
    }

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEvtCnt")
    private List<Canton> fkEvtCnt;*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "canton")
    public Set<Establecimiento> getEstablecimiento() {
        return establecimientos;
    }

    public void setEstablecimiento(Set<Establecimiento> establecimiento) {
        this.establecimientos = establecimiento;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getCntId())
                .append("new", this.isNew())
                .append("area", this.getCntArea())
                .append("descripcion", this.getCntDescripcion())
                .toString();
    }

    public void update( final String nombre,  final Double area,  final String descripcion) {
        this.cntNombre = nombre;
        this.cntArea = area;
        this.cntDescripcion = descripcion;
    }

    public static Builder getBuilder(String nombre, Double area, String descripcion) {
        return new Builder(nombre, area, descripcion);
    }

    public static class Builder {

        private Canton built;

        public Builder(String nombre, Double area, String descripcion) {
            built = new Canton();
            built.cntNombre = nombre;
            built.cntArea = area;
            built.cntDescripcion = descripcion;
        }

        public Canton build() {
            return built;
        }
    }

}
