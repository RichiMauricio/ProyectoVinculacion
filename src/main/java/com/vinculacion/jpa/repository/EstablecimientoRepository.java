package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Establecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mauricio on 27/04/2017.
 */
public interface EstablecimientoRepository extends JpaRepository<Establecimiento,Long>, Serializable {

    List<Establecimiento> findByestNombre(String estNombre);

    @Query("select distinct e from Establecimiento e left join fetch " +
            "e.telefonos p where e.estId = ?1")
    Establecimiento findByContactIdWithDetail(Long ID);

    @Query("select distinct e from Establecimiento e left join fetch " +
            "e.telefonos p")
    List<Establecimiento> findAllWithDetail();

    @Query("select c from Establecimiento c where c.estNombre like %:estNombre%")
    List<Establecimiento> searchByestNombre(@Param("estNombre") String estNombre);

    List<Establecimiento> findByestNombreIgnoreCaseContains(@Param("estNombre") String estNombre);
}
