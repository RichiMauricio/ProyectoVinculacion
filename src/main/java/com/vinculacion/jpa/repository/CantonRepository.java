package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Canton;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Mauricio on 08/05/2017.
 */
public interface CantonRepository extends JpaRepository<Canton,Integer>, Serializable {
}
