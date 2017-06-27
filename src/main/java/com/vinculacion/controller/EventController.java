package com.vinculacion.controller;

import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.model.Evento;
import com.vinculacion.jpa.service.CantonService;
import com.vinculacion.jpa.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
public class EventController {

    private CantonService cantonService;
    private EventService eventService;

    private static final Logger logger = LoggerFactory.getLogger(Evento.class);

    @Autowired
    public EventController(CantonService cantonService, EventService eventService) {
        this.cantonService = cantonService;
        this.eventService = eventService;
    }

    //obtener todos los eventos de un canton
    @RequestMapping(value = "/{cantonId}/events",method = RequestMethod.GET)
    public String listEvents(@PathVariable int cantonId,Model model){
        model.addAttribute("eventos",eventService.getEventsByCantonView(cantonId));
        model.addAttribute("cantonId",cantonId);
        if (cantonId>9){
            return "error/403";

        }
        //        return "/usercanton/events/viewevents";
        return "/events/viewevents";
    }

    //obtener un evento por medio de un identificador
//    @RequestMapping(value = "/{cantonId}/eventos/{id}")
//    public String showEvent(@PathVariable int cantonId, int id, Model model){
//        model.addAttribute("evento",eventService.getEventByCantonAndId(cantonId, id));
//        return "viewEvent";
//    }


    //crear evento
    @RequestMapping(value = "/{cantonId}/events/create")
    public String createEvent(@PathVariable int cantonId,Model model){
        if (cantonId>9){
            return "error/403";
        }
        model.addAttribute("events",new Evento());
        model.addAttribute("cantonId",cantonId);
        return "/events/createevent";
    }

    //guardar el evento
    @RequestMapping(value = "/{cantonId}/events/create",method = RequestMethod.POST)
    public String saveEvent(@Valid Evento event, BindingResult bindingResult){
        try{
            if (bindingResult.hasErrors()){
                return "createevent";
            }else{
                Evento savedEvent= eventService.saveEvent(event);
                System.out.println("este es otro"+event.getCantonEv().getCntId());
                return "redirect:/" + savedEvent.getCantonEv().getCntId() + "/events";
            }
        }catch (Exception e){
            logger.info("ERROR...!!!: " + e.getMessage());
            return "redirect:/events/createevent";
        }
    }

    //editar el evento
    @RequestMapping("/{cantonId}/events/edit/{id}")
    public String editEvent(@PathVariable int cantonId,@PathVariable int id, Model model){
        if (cantonId>9){
            return "error/403";

        }
        model.addAttribute("cantonId",cantonId);
        model.addAttribute("events",eventService.getEventById(id));
//        return "/usercanton/events/createevent";
        return "/events/createevent";
    }

    //eliminar el evento
    @RequestMapping("/{cantonId}/events/delete/{id}")
    public String deleteEvent(@PathVariable int cantonId,@PathVariable int id,Model mode){
        if (cantonId>9){
            return "error/403";

        }
        eventService.deleteEvento(id);
        return "redirect:"+cantonId+"/events";
    }
}
