package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.TipoEstablecimiento;

import java.util.List;

/**
 * Created by Mauricio on 09/06/2017.
 */
public interface TipoEstablecimientoService {
    List<TipoEstablecimiento> findAll();
}
