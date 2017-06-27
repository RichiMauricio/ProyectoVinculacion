package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Correo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mauricio on 05/06/2017.
 */
public interface CorreoRepository extends JpaRepository<Correo,Integer>, Serializable {

    Correo findBycorreoId(int id);
    List<Correo> findByEstablecimiento_estId(int id);
}
