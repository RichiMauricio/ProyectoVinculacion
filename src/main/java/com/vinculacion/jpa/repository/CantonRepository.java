package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

/**
 * Created by Mauricio on 08/05/2017.
 */
public interface CantonRepository extends JpaRepository<Canton,Long>, Serializable {

    @Query("select c.cntNombre from Canton c , Establecimiento e where c.cntId = e.estId and e.estId=?1")
    Canton findByCantonId(Long ID);

}
