package com.vinculacion.jpa.dto;

import com.vinculacion.jpa.model.Correo;
import com.vinculacion.jpa.model.Telefono;
import com.vinculacion.jpa.model.validators.ExtendedEmailValidator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class CorreoDTO {

    private Integer correoId;
    private Integer establecimientoId;

    @NotEmpty
    @ExtendedEmailValidator
    @Length(max = Correo.MAX_LENGTH_CORREO_NOMBRE)
    private String correoNombre;


    public CorreoDTO() {

    }

    public CorreoDTO(String correoNombre) {
        this.correoNombre = correoNombre;
    }

    public CorreoDTO(Correo correoNombre) {
        this.correoId = correoNombre.getCorreoId();
        this.establecimientoId = correoNombre.getEstablecimiento().getEstId();
        this.correoNombre = correoNombre.getCorreoNombre();
    }

    public Integer getCorreoId() {
        return correoId;
    }

    public void setCorreoId(Integer correoId) {
        this.correoId = correoId;
    }

    public Integer getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Integer establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public String getCorreoNombre() {
        return correoNombre;
    }

    public void setCorreoNombre(String correoNombre) {
        this.correoNombre = correoNombre;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
