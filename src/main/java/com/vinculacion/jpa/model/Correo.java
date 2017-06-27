package com.vinculacion.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vinculacion.jpa.dto.CorreoDTO;
import com.vinculacion.jpa.model.validators.ExtendedEmailValidator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mauricio on 05/06/2017.
 */
@Entity
@Table(name = "Correo")
@JsonIgnoreProperties({ "correoId", "establecimiento","new" })
public class Correo {

    private Integer correoId;

    private String correoNombre;
    private Establecimiento establecimiento;

    public static final int MAX_LENGTH_CORREO_NOMBRE = 50;

    public Correo() {}

    public Correo(CorreoDTO correoDTO) {
        this.correoNombre = correoDTO.getCorreoNombre();
    }

    @Transient
    public boolean isNew() {
        return (this.correoId == null);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "correoId", nullable = false, insertable = true, updatable = true)
    public Integer getCorreoId() {
        return correoId;
    }

    public void setCorreoId(Integer correoId) {
        this.correoId = correoId;
    }

    @Basic
    @ExtendedEmailValidator
    @Column(name = "correoNombre", nullable = false, insertable = true, updatable = true, length = MAX_LENGTH_CORREO_NOMBRE)
    @NotEmpty
    public String getCorreoNombre() {
        return correoNombre;
    }

    public void setCorreoNombre(String correoNombre) {
        this.correoNombre = correoNombre;
    }

    @ManyToOne
    @JoinColumn(name = "correoEstablecimiento")
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getCorreoId())
                .append("new", this.isNew())
                .append("estId", this.getEstablecimiento().getEstId())
                .append("correoNombre", this.getCorreoNombre())
                .toString();
    }



    public void update(final String correoNombre) {
        this.correoNombre = correoNombre;
    }

    public static Builder getBuilder(Establecimiento establecimiento, String correoNombre) {
        return new Builder(establecimiento, correoNombre);
    }

    public static class Builder {

        private Correo built;

        public Builder(Establecimiento establecimiento, String correoNombre) {
            built = new Correo();
            built.establecimiento = establecimiento;
            built.correoNombre = correoNombre;
        }

        public Correo build() {
            return built;
        }
    }


}
