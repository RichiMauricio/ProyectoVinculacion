package com.vinculacion.jpa.dto;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.TipoEstablecimiento;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.vinculacion.jpa.model.Establecimiento;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class EstablecimientoDTO {

    private Integer establecimientoId;
    private Canton cntId;
    private TipoEstablecimiento tipoEstId;

    private List<TelefonoDTO> contactPhones;
    private Set<CorreoDTO> correosEst;

    @NotEmpty
    @Length(max = Establecimiento.MAX_LENGTH_ESTABLECIMIENTO_NOMBRE)
    private String estNombre;

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

    private String estDescripcion;

    public String getEstDescripcion() {
        return estDescripcion;
    }

    public void setEstDescripcion(String estDescripcion) {
        this.estDescripcion = estDescripcion;
    }

    private int estAfiliado;


    private boolean updateChildren = true;

    public EstablecimientoDTO() {
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Integer establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public List<TelefonoDTO> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<TelefonoDTO> contactPhones) {
        this.contactPhones = contactPhones;
    }

    public Set<CorreoDTO> getCorreosEst() {
        return correosEst;
    }

    public void setCorreosEst(Set<CorreoDTO> correosEst) {
        this.correosEst = correosEst;
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

    public Canton getCntId() {
        return cntId;
    }

    public void setCntId(Canton cntId) {
        this.cntId = cntId;
    }

    public TipoEstablecimiento getTipoEstId() {
        return tipoEstId;
    }

    public void setTipoEstId(TipoEstablecimiento tipoEstId) {
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
