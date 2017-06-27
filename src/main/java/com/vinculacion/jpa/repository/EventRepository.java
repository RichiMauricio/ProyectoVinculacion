package com.vinculacion.jpa.repository;

import com.vinculacion.jpa.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexis on 25/04/2017.
 */
public interface EventRepository extends JpaRepository<Evento,Integer> {
    /*@Query(value = "select * from events where event_canton=?1", nativeQuery = true)
    List<Evento> findEventsByCanton(int id);

    @Query(value = "select * from events where event_canton=?1 and event_id=?2", nativeQuery = true)
    Evento findEventByCantonAndId(int cantonId, int eventId);
    //Eventos para android
    //publicaciones para evetnos de todso los cantones limitados por 10
//    @Query(value = "select e.*  from events e where e.event_id=3 or e.event_id=21 order by e.event_id desc limit 10 ", nativeQuery = true)
//      @Query("select new Map(e.event_id as event_id,e.event_nombre as event_nombre,e.event_descripcion as event_descripcion,e.even_fecha as even_fecha,e.event_lugar as event_lugar,e.event_logitud as event_logitud,e.event_latitud as event_latitud) from Event e")
      @Query("select e.event_id ,e.event_nombre ,e.event_descripcion ,e.even_fecha ,e.event_lugar ,e.event_logitud ,e.event_latitud  from Event e where e.event_id=3 or e.event_id=21 ")
      List<Evento> findByCantonAllEndEvents();
*/
    @Query(value = "select e from Evento e where event_canton=?1")
    List<Evento> getEventsByCantonCntId(int cantonId);
    //@Query(value = "select * from events where event_canton=?1 and event_id=?2", nativeQuery = true)
    @Query(value = "select e from Evento e where e.eventFechaInicio between ?1 and ?2")
    List<Evento> getEventsByMes(int mes);

}