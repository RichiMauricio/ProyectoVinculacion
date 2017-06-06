package com.vinculacion.jpa.model;

import com.vinculacion.jpa.dto.CorreoDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mauricio on 05/06/2017.
 */
@Entity
@Table(name = "Correo")
public class Correo {

    private Long correoId;

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
    public Long getCorreoId() {
        return correoId;
    }

    public void setCorreoId(Long correoId) {
        this.correoId = correoId;
    }

    @Basic
    @Column(name = "correoNombre", nullable = false, insertable = true, updatable = true, length = MAX_LENGTH_CORREO_NOMBRE)
    @NotEmpty
    public String getCorreoNombre() {
        return correoNombre;
    }

    public void setCorreoNombre(String correoNombre) {
        this.correoNombre = correoNombre;
    }

    @ManyToOne
    @JoinColumn(name = "estId", referencedColumnName = "estId", nullable = false)
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

    public static Correo.Builder getBuilder(Establecimiento establecimiento, String correoNombre) {
        return new Correo.Builder(establecimiento, correoNombre);
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
