package com.vinculacion.controller;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.service.CantonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Created by Mauricio on 08/05/2017.
 */
@Controller
public class CantonController {

    private CantonService cantonService;

    protected static final String MODEL_ATTRIBUTE_CANTONES = "cantones";

    //Lista de los cantones para el establecimiento
    @ModelAttribute(MODEL_ATTRIBUTE_CANTONES)
    public List<Canton> allCantones() {
        return cantonService.findAll();
    }
}
