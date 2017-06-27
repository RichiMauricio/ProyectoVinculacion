package com.vinculacion.jpa.dto;

import com.vinculacion.jpa.model.Canton;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

/**
 * Created by Mauricio on 06/06/2017.
 */
public class CantonDTO {

    private Integer cantonId;
    private Set<EstablecimientoDTO> establecimientos;

    @NotEmpty
    @Length(max = Canton.DIMENSION_NOMBRE_CANTON)
    private String cntNombre;

    private String cntArea;

    @Length(max = Canton.DIMENSION_DESCRIPCION_CANTON)
    private String cntDescripcion;

    private boolean updateChildren = true;

    public CantonDTO() {
    }

    public Integer getCantonId() {
        return cantonId;
    }

    public void setCantonId(Integer cantonId) {
        this.cantonId = cantonId;
    }

    public Set<EstablecimientoDTO> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(Set<EstablecimientoDTO> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public String getCntNombre() {
        return cntNombre;
    }

    public void setCntNombre(String cntNombre) {
        this.cntNombre = cntNombre;
    }

    public String getCntArea() {
        return cntArea;
    }

    public void setCntArea(String cntArea) {
        this.cntArea = cntArea;
    }

    public String getCntDescripcion() {
        return cntDescripcion;
    }

    public void setCntDescripcion(String cntDescripcion) {
        this.cntDescripcion = cntDescripcion;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isNew() {
        return (this.cantonId == null);
    }


    public boolean isUpdateChildren() {
        return updateChildren;
    }

    public void setUpdateChildren(boolean updateChildren) {
        this.updateChildren = updateChildren;
    }

}
