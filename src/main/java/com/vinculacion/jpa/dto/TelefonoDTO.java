package com.vinculacion.jpa.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.vinculacion.jpa.model.Telefono;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class TelefonoDTO {

    private Integer telefonoId;
    private Integer establecimientoId;

    @NotEmpty
    @Length(max = Telefono.MAX_LENGTH_PHONE_TYPE)
    private String phoneType;

    @NotEmpty
    @Length(max = Telefono.MAX_LENGTH_PHONE_NUMBER)
    private String phoneNumber;


    public TelefonoDTO() {

    }

    public TelefonoDTO(String phoneType, String phoneNumber) {
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public TelefonoDTO(Telefono contactPhone) {
        this.telefonoId = contactPhone.getTlfId();
        this.establecimientoId = contactPhone.getEstablecimiento().getEstId();
        this.phoneType = contactPhone.getTlfTipo();
        this.phoneNumber = contactPhone.getTlfNumero();
    }

    public Integer getTelefonoId() {
        return telefonoId;
    }

    public void setTelefonoId(Integer contactPhoneId) {
        this.telefonoId = contactPhoneId;
    }

    public Integer getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Integer establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
