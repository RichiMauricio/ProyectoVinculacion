package com.vinculacion.jpa.service;

import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;

import java.util.List;
import com.vinculacion.jpa.dto.*;
import com.vinculacion.jpa.exceptions.EstablecimientoNotFoundException;
import com.vinculacion.jpa.model.Telefono;

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

    Establecimiento findEstablecimientoById(Long ID) throws EstablecimientoNotFoundException;
    //Contact getContactByEmail(String email);
    Establecimiento getEstablecimientoByIdWithDetail(Long ID);
    //Contact deleteById(Long id) throws ContactNotFoundException;

    // endregion

    // region Contact Phones -------------------------------------- */

    List<Telefono> findContactPhonesByestId(Long contactId);
    Telefono addContactPhone(TelefonoDTO contactPhoneDTO);
    Telefono findContactPhoneById(Long contactPhoneID);
    Telefono deleteEstablecimientoPhoneById(Long contactPhoneId) throws EstablecimientoNotFoundException;

    // endregion
}
