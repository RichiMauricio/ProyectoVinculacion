package com.vinculacion.jpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
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
//@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Canton")
@Access(PROPERTY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Canton implements Serializable{

    private Integer cntId;
    private String cntNombre;
    private Double cntArea;
    private String cntDescripcion;
    private Integer cntPoblacion;
    private Set<Establecimiento> establecimientos;
    //private List<Evento> eventos;

    public static final int DIMENSION_NOMBRE_CANTON = 40;
    public static final int DIMENSION_DESCRIPCION_CANTON = 3000;

    @Transient
    public boolean isNew() {
        return (this.cntId == null);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cntId")
    public Integer getCntId() {
        return cntId;
    }

    public void setCntId(Integer cntId) {
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

    @Column(name = "cntPoblacion", unique = false, nullable = true, updatable = true)
    public Integer getCntPoblacion() {
        return cntPoblacion;
    }

    public void setCntPoblacion(Integer poblacion) {
        this.cntPoblacion = poblacion;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "canton")
    public Set<Establecimiento> getEstablecimiento() {
        return establecimientos;
    }

    public void setEstablecimiento(Set<Establecimiento> establecimiento) {
        this.establecimientos = establecimiento;
    }

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "cantonEv")

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getCntId())
                .append("new", this.isNew())
                .append("area", this.getCntArea())
                .append("descripcion", this.getCntDescripcion())
                .append("poblacion", this.getCntPoblacion())
                .toString();
    }

    public void update( final String nombre,  final Double area,  final String descripcion) {
        this.cntNombre = nombre;
        this.cntArea = area;
        this.cntDescripcion = descripcion;
    }

    public static Builder getBuilder(String nombre, Double area, String descripcion, Integer poblacion) {
        return new Builder(nombre, area, descripcion, poblacion);
    }

    public static class Builder {

        private Canton built;

        public Builder(String nombre, Double area, String descripcion, Integer poblacion) {
            built = new Canton();
            built.cntNombre = nombre;
            built.cntArea = area;
            built.cntDescripcion = descripcion;
            build().cntPoblacion = poblacion;
        }

        public Canton build() {
            return built;
        }
    }

}
