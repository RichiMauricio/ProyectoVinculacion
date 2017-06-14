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

    @Query("select c from Establecimiento c where c.estNombre like %:estNombre% ")
    List<Establecimiento> findByEstNombre(@Param("estNombre") String estNombre);

    @Query("select c from Establecimiento c where c.estRepresentante like %:estRepresentante% ")
    List<Establecimiento> findByestRepresentante(@Param("estRepresentante") String estRepresentante);

    @Query("select c from Establecimiento c where c.estRepresentante like %:estRepresentante% or c.canton like %:canton%")
    List<Establecimiento> findByParams(@Param("estRepresentante") String estRepresentante, @Param("canton") Long canton);

    //Buscar establecimientos poe cantón y tipo de establecimiento
    @Query("select c from Establecimiento c where c.canton like %:canton% and c.tipoEstablecimiento like %:tipoEst%")
    List<Establecimiento> findByCantonyTipo(int canton, int tipoEst);

    List<Establecimiento> findByestNombreIgnoreCaseContains(@Param("estNombre") String estNombre);
}
