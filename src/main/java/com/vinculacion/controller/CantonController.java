package com.vinculacion.controller;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.service.CantonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Mauricio on 08/05/2017.
 */
@Controller
public class CantonController {

    @Autowired  //IMPORTANTE...!!! sin esto el mapeo no funciona y apunta a null
            CantonService cantonService;

}
