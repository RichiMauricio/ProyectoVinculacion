package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Evento;
import com.vinculacion.jpa.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexis on 25/04/2017.
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    /*
   //    public List<Event> listAllEvents(int cantonId){
   //        return eventRepository.findEventByCanton(cantonId);
   //    }

       //selects para Eventos de los jsons se puede cambiar y dar todos los eventos con @json include
       public List<Evento> getEventsByCanton(int cantonId){
           return eventRepository.findEventsByCanton(cantonId);
       }

       public List<Evento> listEndEvents(){return eventRepository.findByCantonAllEndEvents();}
       */
    //slects para eventos de la pagina viewevents
    public Evento getEventById(int cantonId){
        return  eventRepository.findOne(cantonId);
    }

    public List<Evento> getEventsByCantonView(int cantonId){
        return eventRepository.getEventsByCantonCntId(cantonId);
    }

    public Evento saveEvent(Evento event){
        return  eventRepository.save(event);
    }

    public void deleteEvento(int id){
        eventRepository.delete(id);
    }

    public List<Evento> getAllEvents(){
        return eventRepository.findAll();
    }

    public List<Evento> getAllEventsByMonth(int cntId){
        return eventRepository.getEventsByCantonCntId(cntId);
    }
}

