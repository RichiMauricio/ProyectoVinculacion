package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.repository.CantonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mauricio on 08/05/2017.
 */
@Service
public class CantonService {

    @Autowired
    CantonRepository cantonRepository;

    //Servicio para listar etablecimientos
    public List<Canton> listarCantones() {
        return cantonRepository.findAll();
    }
}
