package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.TipoEstablecimiento;
import com.vinculacion.jpa.repository.TipoEstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mauricio on 08/05/2017.
 */
@Service
public class TipoEstablecimientoService {

    @Autowired
    TipoEstablecimientoRepository tipoEstablecimientoRepository;

    //Servicio para listar los tipos establecimientos
    public List<TipoEstablecimiento> listarTipos() {
        return tipoEstablecimientoRepository.findAll();
    }

}
