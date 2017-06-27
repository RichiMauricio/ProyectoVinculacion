package com.vinculacion.rest;

import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.model.Evento;
import com.vinculacion.jpa.service.EstablecimientoService;
import com.vinculacion.jpa.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mauricio on 27/06/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class EventoRController {

    @Autowired
    private EventService eventService;

    private static final Logger logger = LoggerFactory.getLogger(Establecimiento.class);

    //Retornamos todos los establecimientos
    @RequestMapping(value = "/events")
    public List<Evento> getEvents() {
        return eventService.getAllEvents();
    }

    @RequestMapping(value = "/{cntId}/events/mes")
    public List<Evento> getEventsByMonth(@PathVariable("cntId") int id) {
        try{
            LocalDateTime fechaActual = LocalDateTime.now();
            LocalDateTime fechaSig = fechaActual.plusMonths(1);
            List<Evento> eventos = eventService.getEventsByCantonView(id);
            Iterator<Evento> evento = eventos.iterator();
            ArrayList<Evento> eventosByFecha = new ArrayList<Evento>();
            while (evento.hasNext()){
                Evento temporal = evento.next();
                if (temporal.getEventFechaInicio()!=null && temporal.getEventFechaFin()!=null) {
                    if (temporal.getEventFechaInicio().getMonthValue() >= fechaActual.getMonthValue() && temporal.getEventFechaInicio().getMonthValue() < fechaSig.getMonthValue()) {
                        eventosByFecha.add(temporal);
                    }
                }
            }
            return eventosByFecha;
        }catch(Exception e){
            logger.info("Error....!!!!: " + e.getMessage()+ " " + e.getLocalizedMessage());
            return null;
        }

    }
}
