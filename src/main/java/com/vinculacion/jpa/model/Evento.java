package com.vinculacion.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexis on 21/04/2017.
 */

@Entity
@Data
@Table(name="evento")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"eventId","cantonEv"})
public class Evento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int eventId;
    private String eventNombre;
    private String eventDescripcion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  eventFechaInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  eventFechaFin;

    private String eventLugar;
    private String eventLongitud;
    private String eventLatitud;
    private String eventImg;

    @ManyToOne
    @JoinColumn(name = "eventCanton")
    private Canton cantonEv;

    public Evento(String eventNombre, String eventDescripcion, LocalDateTime  evenFechaInicio,LocalDateTime  evenFechaFin, String eventLugar, String eventLogitud, String eventLatitud, String eventImg) {
        this.eventNombre = eventNombre;
        this.eventDescripcion = eventDescripcion;
        this.eventFechaInicio = evenFechaInicio;
        this.eventFechaFin = evenFechaFin;
        this.eventLugar = eventLugar;
        this.eventLongitud = eventLogitud;
        this.eventLatitud = eventLatitud;
        this.eventImg = eventImg;
    }

}
