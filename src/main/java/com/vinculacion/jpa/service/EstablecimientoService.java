package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.*;

import java.util.List;
import com.vinculacion.jpa.dto.*;
import com.vinculacion.jpa.exceptions.EstablecimientoNotFoundException;

/**
 * Created by Mauricio on 05/06/2017.
 */
public interface EstablecimientoService {

    // region Establecimientos -------------------------------------- */

    List<Establecimiento> findAll();
    //List<Establecimiento> findByFirstName(String firstName);
    //List<Establecimiento> findByFirstNameAndLastName(String firstName, String lastName);
    //List<Establecimiento> getContactsWithDetail();
    List<Establecimiento> searchByNombre(String nombre);

    Establecimiento add(EstablecimientoDTO added);
    Establecimiento update(EstablecimientoDTO updated) throws EstablecimientoNotFoundException;

    Establecimiento findEstablecimientoById(int ID) throws EstablecimientoNotFoundException;
    List<Establecimiento> getEstablecimientoByNombre(String estNombre);
    List<Establecimiento> getEstablecimientoByNombreCntTip(String estNombre, int canton, int tipoEst);
    List<Establecimiento> getEstablecimientoByRepresentante(String estRepresentante);
    List<Establecimiento> getEstablecimientoByCantonyTipo(int cntId, int tipoEst);
    List<Establecimiento> getEstablecimientoByParams(String estRepresentante, int canton);
    //List<Establecimiento> getEstablecimientosByCanton(Canton cntId, TipoEstablecimiento tipoEstabId);
    Establecimiento getEstablecimientoByIdWithDetail(int ID);
    Establecimiento deleteById(int id) throws EstablecimientoNotFoundException;

    // endregion

    // region Contact Phones -------------------------------------- */

    List<Telefono> findContactPhonesByestId(int contactId);
    Telefono addContactPhone(TelefonoDTO contactPhoneDTO);
    Telefono findContactPhoneById(int contactPhoneID);
    Telefono deleteEstablecimientoPhoneById(int contactPhoneId) throws EstablecimientoNotFoundException;
    Correo deleteCorreoEstablecimientoById(int correoId) throws EstablecimientoNotFoundException;

    // endregion
}
