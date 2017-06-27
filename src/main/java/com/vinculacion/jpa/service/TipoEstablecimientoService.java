package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.model.TipoEstablecimiento;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Mauricio on 09/06/2017.
 */
public interface TipoEstablecimientoService {

    List<TipoEstablecimiento> findAll();

    @Query("select c.cntNombre from Canton c , Establecimiento e where c.cntId = e.estId and e.estId=?1")
    TipoEstablecimiento findByTipoEstId(int ID);
}
