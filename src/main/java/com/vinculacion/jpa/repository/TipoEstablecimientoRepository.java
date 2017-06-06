package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.TipoEstablecimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Mauricio on 08/05/2017.
 */
public interface TipoEstablecimientoRepository extends JpaRepository<TipoEstablecimiento,Integer>, Serializable{
}
