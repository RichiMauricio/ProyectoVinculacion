package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Correo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mauricio on 05/06/2017.
 */
public interface CorreoRepository extends JpaRepository<Correo,Long>, Serializable {

    Correo findBycorreoId(Long id);
    List<Correo> findByEstablecimiento_estId(Long id);
}
