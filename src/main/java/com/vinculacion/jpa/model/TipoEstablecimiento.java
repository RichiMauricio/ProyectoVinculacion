package com.vinculacion.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.style.ToStringCreator;

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
@Table(name = "TipoEstablecimiento")
@Access(PROPERTY)
public class TipoEstablecimiento implements Serializable{

    private Long tipoEstId;
    private String tipoEstNombre;
    private Set<Establecimiento> establecimientos;

    public static final int DIMENSION_TIPO_ESTABLECIMIENTO = 50;

    @Transient
    public boolean isNew() {
        return (this.tipoEstId == null);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tipoEstId")
    public Long getTipoEstId() {
        return tipoEstId;
    }

    public void setTipoEstId(Long tipoEstId) {
        this.tipoEstId = tipoEstId;
    }

    @Basic
    @Column(name = "tipoEstNombre", insertable = true, updatable = true, length = DIMENSION_TIPO_ESTABLECIMIENTO)
    public String getTipoEstNombre() {
        return tipoEstNombre;
    }

    public void setTipoEstNombre(String tipoEstNombre) {
        this.tipoEstNombre = tipoEstNombre;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEstablecimiento")
    public Set<Establecimiento> getEstablecimiento() {
        return establecimientos;
    }

    public void setEstablecimiento(Set<Establecimiento> establecimiento) {
        this.establecimientos = establecimiento;
    }

    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getTipoEstId())
                .append("new", this.isNew())
                .append("tipoEstablecimiento", this.getTipoEstNombre())
                .toString();
    }

    public void update( final String tipoEstNombre) {
        this.tipoEstNombre = tipoEstNombre;
    }

    public static TipoEstablecimiento.Builder getBuilder(String nombre) {
        return new TipoEstablecimiento.Builder(nombre);
    }

    public static class Builder {

        private TipoEstablecimiento built;

        public Builder(String nombre) {
            built = new TipoEstablecimiento();
            built.tipoEstNombre = nombre;
        }

        public TipoEstablecimiento build() {
            return built;
        }
    }

}
