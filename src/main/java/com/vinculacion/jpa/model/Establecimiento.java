package com.vinculacion.jpa.model;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.AccessType.PROPERTY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mauricio on 19/04/2017.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Establecimiento")
@Access(PROPERTY)
public class Establecimiento implements Serializable{

    private Long estId;
    private String estNombre;
    private String estRepresentante;
    private String estDireccion;
    private String estPagina;
    private String estLatitud;
    private String estLongitud;
    private String estFicheroImagenes;
    private int estAfiliado;
    private Set<Telefono> telefonos;
    private Set<Correo> correos;
    private Long canton;
    private Long tipoEstablecimiento;

    public static final int MAX_LENGTH_ESTABLECIMIENTO_NOMBRE = 40;
    public static final int MAX_LENGTH_ESTABLECIMIENTO_REPRESENTANTE = 50;
    public static final int MAX_LENGTH_ESTABLECIMIENTO_DIRECCION = 100;
    public static final int MAX_LENGTH_ESTABLECIMIENTO_PAGINA = 60;
    public static final int MAX_LENGTH_ESTABLECIMIENTO_FICEHROIMAGENES = 150;


    @Transient
    public boolean isNew() {
        return (this.estId == null);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "estId")
    public Long getEstId() {
        return estId;
    }

    public void setEstId(Long estId) {
        this.estId = estId;
    }

    @Column(name = "estNombre", unique = false, nullable = false)
    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    @Column(name = "estRepresentante", unique = false, nullable = true)
    public String getEstRepresentante() {
        return estRepresentante;
    }

    public void setEstRepresentante(String estRepresentante) {
        this.estRepresentante = estRepresentante;
    }

    @Column(name = "estDireccion", unique = false, nullable = false)
    public String getEstDireccion() {
        return estDireccion;
    }

    public void setEstDireccion(String estDireccion) {
        this.estDireccion = estDireccion;
    }

    @Column(name = "estAfiliado", unique = false, nullable = true)
    public int getEstAfiliado() {
        return estAfiliado;
    }

    public void setEstAfiliado(int estAfiliado) {
        this.estAfiliado = estAfiliado;
    }

    @Column(name = "estPagina", unique = false, nullable = true)
    public String getEstPagina() {
        return estPagina;
    }

    public void setEstPagina(String estPagina) {
        this.estPagina = estPagina;
    }

    @Column(name = "estLatitud", unique = false, nullable = true)
    public String getEstLatitud() {
        return estLatitud;
    }

    public void setEstLatitud(String estLatitud) {
        this.estLatitud = estLatitud;
    }

    @Column(name = "estLongitud", unique = false, nullable = true)
    public String getEstLongitud() {
        return estLongitud;
    }

    public void setEstLongitud(String estLongitud) {
        this.estLongitud = estLongitud;
    }

    @Column(name = "estFicheroImagenes", unique = false, nullable = true)
    public String getEstFicheroImagenes() {
        return estFicheroImagenes;
    }

    public void setEstFicheroImagenes(String estFicheroImagenes) {
        this.estFicheroImagenes = estFicheroImagenes;
    }

    @Column(name = "canton", unique = false, nullable = false)
    public Long getCanton() {
        return canton;
    }

    public void setCanton(Long canton) {
        this.canton = canton;
    }

    @Column(name = "tipoEstablecimiento", unique = false, nullable = false)
    public Long getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public void setTipoEstablecimiento(Long tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimiento")
    public Set<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establecimiento")
    public Set<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(Set<Correo> correos) {
        this.correos = correos;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getEstId())
                .append("new", this.isNew())
                .append("nombre", this.getEstNombre())
                .append("representante", this.getEstRepresentante())
                .append("direccion", this.getEstDireccion())
                .append("pagina", this.getEstPagina())
                .append("longitud", this.getEstLongitud())
                .append("latitud", this.getEstLatitud())
                .append("ficheroDeImagenes", this.getEstFicheroImagenes())
                .append("afiliado", this.getEstAfiliado())
                .append("cntId", this.getCanton())
                .append("tipoEstId", this.getTipoEstablecimiento())
                .toString();
    }

    public void update( final String nombre, final String representante, final String direccion, final String pagina,
                        final String longitud, final String latitud, final String ficheroDeImagenes, final int afiliado) {
        this.estNombre = nombre;
        this.estRepresentante = representante;
        this.estDireccion = direccion;
        this.estPagina = pagina;
        this.estLongitud = longitud;
        this.estLatitud = latitud;
        this.estFicheroImagenes = ficheroDeImagenes;
        this.estAfiliado = afiliado;
    }

    public static Builder getBuilder(Long tipoEst, Long cntId, String nombre, String representante, String direccion, String pagina,
                                     String longitud, String latitud, String ficheroDeImagenes, int afiliado) {
        return new Establecimiento.Builder(tipoEst, cntId, nombre, representante, direccion, pagina, longitud, latitud, ficheroDeImagenes,
                afiliado);
    }

    public static class Builder {

        private Establecimiento built;

        public Builder(Long tipoEst, Long cntId, String nombre, String representante, String direccion, String pagina,
                       String longitud, String latitud, String ficheroDeImagenes, int afiliado) {
            built = new Establecimiento();
            built.estNombre = nombre;
            built.estRepresentante = representante;
            built.estDireccion = direccion;
            built.estPagina = pagina;
            built.estLongitud = longitud;
            built.estLatitud = latitud;
            built.estFicheroImagenes = ficheroDeImagenes;
            built.estAfiliado = afiliado;
            built.canton = cntId;
            built.tipoEstablecimiento = tipoEst;
        }
        public Establecimiento build() {
            return built;
        }
    }
}
