package com.vinculacion.jpa.model;

import com.vinculacion.jpa.dto.TelefonoDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mauricio on 19/04/2017.
 */
@Entity
@Table(name = "Telefono")
public class Telefono {

    private Long tlfId;

    private String tlfTipo;
    private String tlfNumero;
    private Establecimiento establecimiento;

    public static final int MAX_LENGTH_PHONE_TYPE = 20;
    public static final int MAX_LENGTH_PHONE_NUMBER = 20;

    public Telefono() {}

    public Telefono(TelefonoDTO contactPhoneDTO) {
        this.tlfTipo = contactPhoneDTO.getPhoneType();
        this.tlfNumero = contactPhoneDTO.getPhoneNumber();
    }

    @Transient
    public boolean isNew() {
        return (this.tlfId == null);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tlfId", nullable = false, insertable = true, updatable = true)
    public Long getTlfId() {
        return tlfId;
    }

    public void setTlfId(Long tlfId) {
        this.tlfId = tlfId;
    }

    @Basic
    @Column(name = "tlfTipo", nullable = false, insertable = true, updatable = true, length = MAX_LENGTH_PHONE_TYPE)
    @NotEmpty
    public String getTlfTipo() {
        return tlfTipo;
    }

    public void setTlfTipo(String telType) {
        this.tlfTipo = telType;
    }

    @Basic
    @Column(name = "tlfNumero", nullable = false, insertable = true, updatable = true, length = MAX_LENGTH_PHONE_NUMBER)
    @NotEmpty
    public String getTlfNumero() {
        return tlfNumero;
    }

    public void setTlfNumero(String telNumber) {
        this.tlfNumero = telNumber;
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
                .append("id", this.getTlfId())
                .append("new", this.isNew())
                .append("estId", this.getEstablecimiento().getEstId())
                .append("tipoNumero", this.getTlfTipo())
                .append("numero", this.getTlfNumero())
                .toString();
    }



    public void update(final String phoneType, final String phoneNumber) {
        this.tlfTipo = phoneType;
        this.tlfNumero = phoneNumber;
    }

    public static Builder getBuilder(Establecimiento establecimiento, String phoneType, String phoneNumber) {
        return new Builder(establecimiento, phoneType, phoneNumber);
    }

    public static class Builder {

        private Telefono built;

        public Builder(Establecimiento establecimiento, String phoneType, String phoneNumber) {
            built = new Telefono();
            built.establecimiento = establecimiento;
            built.tlfTipo = phoneType;
            built.tlfNumero = phoneNumber;
        }

        public Telefono build() {
            return built;
        }
    }


}
