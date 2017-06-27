package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.TipoEstablecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

/**
 * Created by Mauricio on 08/05/2017.
 */
public interface TipoEstablecimientoRepository extends JpaRepository<TipoEstablecimiento,Integer>, Serializable{

    @Query("select c from TipoEstablecimiento c , Establecimiento e where c.tipoEstId = e.estId and e.estId=?1")
    TipoEstablecimiento findByTipoId(int ID);
}
