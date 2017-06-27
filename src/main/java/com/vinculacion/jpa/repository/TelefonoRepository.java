package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mauricio on 05/06/2017.
 */
public interface TelefonoRepository extends JpaRepository<Telefono,Integer>, Serializable {
    Telefono findBytlfId(int id);
    List<Telefono> findByEstablecimiento_estId(int id);
}
