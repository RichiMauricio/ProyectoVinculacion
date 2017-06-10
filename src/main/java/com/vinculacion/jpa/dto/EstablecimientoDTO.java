package com.vinculacion.jpa.dto;

import com.vinculacion.jpa.model.Canton;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.vinculacion.jpa.model.Establecimiento;
import java.util.Date;
import java.util.Set;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class EstablecimientoDTO {

    private Long establecimientoId;
    private Long cntId;
    private Long tipoEstId;

    private Set<TelefonoDTO> contactPhones;

    @NotEmpty
    @Length(max = Establecimiento.MAX_LENGTH_ESTABLECIMIENTO_NOMBRE)
    private String estNombre;

    @NotEmpty
    @Length(max = Establecimiento.MAX_LENGTH_ESTABLECIMIENTO_REPRESENTANTE)
    private String estRepresentante;

    @NotEmpty
    @Length(max = Establecimiento.MAX_LENGTH_ESTABLECIMIENTO_DIRECCION)
    private String estDireccion;

    @Length(max = Establecimiento.MAX_LENGTH_ESTABLECIMIENTO_PAGINA)
    private String estPagina;

    @Length(max = Establecimiento.MAX_LENGTH_ESTABLECIMIENTO_FICEHROIMAGENES)
    private String estFicheroImagenes;

    private String estLongitud;

    private String estLatitud;

    private int estAfiliado;


    private boolean updateChildren = true;

    public EstablecimientoDTO() {
    }

    public Long getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Set<TelefonoDTO> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(Set<TelefonoDTO> contactPhones) {
        this.contactPhones = contactPhones;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public String getEstRepresentante() {
        return estRepresentante;
    }

    public void setEstRepresentante(String estRepresentante) {
        this.estRepresentante = estRepresentante;
    }

    public String getEstDireccion() {
        return estDireccion;
    }

    public void setEstDireccion(String estDireccion) {
        this.estDireccion = estDireccion;
    }

    public String getEstPagina() {
        return estPagina;
    }

    public void setEstPagina(String estPagina) {
        this.estPagina = estPagina;
    }

    public String getEstFicheroImagenes() {
        return estFicheroImagenes;
    }

    public void setEstFicheroImagenes(String estFicheroImagenes) {
        this.estFicheroImagenes = estFicheroImagenes;
    }

    public String getEstLongitud() {
        return estLongitud;
    }

    public void setEstLongitud(String estLongitud) {
        this.estLongitud = estLongitud;
    }

    public String getEstLatitud() {
        return estLatitud;
    }

    public void setEstLatitud(String estLatitud) {
        this.estLatitud = estLatitud;
    }

    public int getEstAfiliado() {
        return estAfiliado;
    }

    public void setEstAfiliado(int estAfiliado) {
        this.estAfiliado = estAfiliado;
    }

    public Long getCntId() {
        return cntId;
    }

    public void setCntId(Long cntId) {
        this.cntId = cntId;
    }

    public Long getTipoEstId() {
        return tipoEstId;
    }

    public void setTipoEstId(Long tipoEstId) {
        this.tipoEstId = tipoEstId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isNew() {
        return (this.establecimientoId == null);
    }

    public boolean isUpdateChildren() {
        return updateChildren;
    }

    public void setUpdateChildren(boolean updateChildren) {
        this.updateChildren = updateChildren;
    }

}
