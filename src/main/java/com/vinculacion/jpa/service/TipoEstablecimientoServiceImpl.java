package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.TipoEstablecimiento;
import com.vinculacion.jpa.repository.TipoEstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mauricio on 08/05/2017.
 */
@Service("tipoEstablecimientoService")
@Transactional
public class TipoEstablecimientoServiceImpl implements TipoEstablecimientoService {

    @Autowired
    TipoEstablecimientoRepository tipoEstablecimientoRepository;

    //Servicio para listar los tipos establecimientos
    @Transactional(readOnly = true)
    public List<TipoEstablecimiento> findAll() {
        return tipoEstablecimientoRepository.findAll();
    }

    @Override
    public TipoEstablecimiento findByTipoEstId(int tipoEstId) {
        return tipoEstablecimientoRepository.findByTipoId(tipoEstId);
    }

}
