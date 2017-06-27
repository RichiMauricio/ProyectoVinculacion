package com.vinculacion.rest;

import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.service.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Mauricio on 16/06/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class EstablecimientoRController {

    @Autowired
    private EstablecimientoService establecimientoService;

    //Retornamos todos los establecimientos
    @RequestMapping(value = "/canton/{cantonId}/establecimiento/{categoriaId}")
    public List<Establecimiento> getEstablishments( @PathVariable("cantonId") int cantonId,
                                                                     @PathVariable("categoriaId") int categoriaId) {
        return establecimientoService.getEstablecimientoByCantonyTipo(cantonId,categoriaId);
    }
}
