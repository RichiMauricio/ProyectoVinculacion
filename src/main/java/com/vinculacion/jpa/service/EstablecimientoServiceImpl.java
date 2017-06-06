package com.vinculacion.jpa.service;

import com.google.common.collect.Lists;
import com.vinculacion.jpa.dto.EstablecimientoDTO;
import com.vinculacion.jpa.dto.TelefonoDTO;
import com.vinculacion.jpa.exceptions.EstablecimientoNotFoundException;
import com.vinculacion.jpa.model.Canton;
import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.model.Telefono;
import com.vinculacion.jpa.repository.EstablecimientoRepository;
import com.vinculacion.jpa.repository.TelefonoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Mauricio on 27/04/2017.
 */
@Service("establecimientoService")
@Transactional
public class EstablecimientoServiceImpl implements EstablecimientoService{

    private static final Logger logger = LoggerFactory.getLogger(EstablecimientoService.class);

    // region Beans ------------------------- */

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private TelefonoRepository telefonoRepository;


    @PersistenceContext
    private EntityManager em;

    // endregion

    // region Establecimientos ------------------------- */

    @Transactional(readOnly = true)
    public List<Establecimiento> findAll() {
        return Lists.newArrayList(establecimientoRepository.findAll());
    }
    /*
    @Transactional(readOnly = true)
    public List<Contact> findByFirstName(String firstName) {
        return contactRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByFirstNameAndLastName(
            String firstName, String lastName) {
        return contactRepository.findByFirstNameAndLastName(firstName, lastName);
    }
*/
    @Transactional(readOnly = true)
    public List<Establecimiento> searchByNombre(String nombre) {
        return establecimientoRepository.findByestNombreIgnoreCaseContains(nombre);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> getEstablecimientosWithDetail() {
        return establecimientoRepository.findAllWithDetail();
    }

    @Transactional(readOnly = true)
    public Establecimiento getEstablecimientoByIdWithDetail(Long ID) {
        return establecimientoRepository.findByContactIdWithDetail(ID);
    }

    @Transactional(readOnly = true)
    public Establecimiento findEstablecimientoById(Long ID) throws EstablecimientoNotFoundException {

        logger.debug("Finding contact by id: {}", ID);

        Establecimiento found = establecimientoRepository.findOne(ID);

        if (found == null) {
            logger.info("No contact found with id: {}", ID);
            throw new EstablecimientoNotFoundException("No contact found with id: " + ID);
        }

        return found;
    }

/*
    @Transactional(rollbackFor = ContactNotFoundException.class)
    @Override
    public Contact deleteById(Long id) throws ContactNotFoundException {
        logger.info("Deleting contact by id: {}", id);

        Contact deleted = findContactById(id);
        contactRepository.delete(deleted);

        logger.debug("Deleted contact: {}", deleted);
        return deleted;
    }*/

    @Transactional(rollbackFor = EstablecimientoNotFoundException.class)
    @Override
    public Establecimiento update(EstablecimientoDTO establecimientoDTO) throws EstablecimientoNotFoundException {
        logger.info("Actualizando establecimiento on información: {}", establecimientoDTO);

        Establecimiento found = findEstablecimientoById(establecimientoDTO.getEstablecimientoId());

        // Update the Establecimiento information
        found.update(establecimientoDTO.getEstNombre(), establecimientoDTO.getEstRepresentante(), establecimientoDTO.getEstDireccion(), establecimientoDTO.getEstPagina(), establecimientoDTO.getEstLongitud(), establecimientoDTO.getEstLatitud(), establecimientoDTO.getEstFicheroImagenes(), establecimientoDTO.getEstAfiliado());
        // Update the Establecimiento phone if updateChildren(true)

        if (establecimientoDTO.isUpdateChildren()) {

            if (establecimientoDTO.getContactPhones() != null) {
                for (TelefonoDTO telefonoDTO : establecimientoDTO.getContactPhones()) {
                    Telefono telefono = telefonoRepository.findBytlfId(telefonoDTO.getTelefonoId());
                    if (telefono != null) {
                        telefono.update(telefonoDTO.getPhoneType(), telefonoDTO.getPhoneNumber());
                    } else {
                        telefono = saveContactPhone(found, telefonoDTO);
                        found.getTelefonos().add(telefono);
                    }
                }
            }
        }
        return found;
    }

    @Transactional
    @Override
    public Establecimiento add(EstablecimientoDTO establecimientoDTO) {
        logger.info("Agregando nuevo Establecimiento con información: {}", establecimientoDTO);

        //Creates an instance of an establecimiento by using the builder pattern
        Establecimiento establecimiento = Establecimiento.getBuilder(establecimientoDTO.getCntId(), establecimientoDTO.getEstNombre(),
                establecimientoDTO.getEstRepresentante(), establecimientoDTO.getEstDireccion(),establecimientoDTO.getEstPagina(),
                establecimientoDTO.getEstLongitud(), establecimientoDTO.getEstLatitud(),establecimientoDTO.getEstFicheroImagenes(),
                establecimientoDTO.getEstAfiliado())
                .build();

        Establecimiento saved = establecimientoRepository.save(establecimiento);

        if (establecimientoDTO.getContactPhones() != null) {
            for (TelefonoDTO telefonoDTO : establecimientoDTO.getContactPhones()) {
                Telefono contactPhone = Telefono.getBuilder(saved,
                        telefonoDTO.getPhoneType(),
                        telefonoDTO.getPhoneNumber())
                        .build();

                telefonoRepository.save(contactPhone);
            }
        }

        em.refresh(saved);

        return saved;

    }

    // endregion

    // region Contact Phones ---------------------- */

    @Override
    public Telefono deleteEstablecimientoPhoneById(Long contactPhoneId) throws EstablecimientoNotFoundException {

        Telefono contactPhone = telefonoRepository.findOne(contactPhoneId);
        if (contactPhone != null) {
            logger.info("Removing Establecimiento phone with information: {}", contactPhone);
            telefonoRepository.delete(contactPhone);
        }

        return contactPhone;
    }

    @Transactional(readOnly = true)
    public List<Telefono> findContactPhonesByestId(Long contactId) {
        return telefonoRepository.findByEstablecimiento_estId(contactId);
    }

    @Override
    @Transactional(rollbackFor = EstablecimientoNotFoundException.class)
    public Telefono addContactPhone(TelefonoDTO telefonoDTO) {
        Establecimiento establecimiento = establecimientoRepository.findOne(telefonoDTO.getTelefonoId());
        Telefono contactPhone = Telefono.getBuilder(establecimiento,
                telefonoDTO.getPhoneType(),
                telefonoDTO.getPhoneNumber())
                .build();

        return telefonoRepository.save(contactPhone);
    }

    @Override
    public Telefono findContactPhoneById(Long contactPhoneID) {
        return telefonoRepository.findBytlfId(contactPhoneID);
    }

    // endregion

    // region Private Establecimiento Phone Method
    @Transactional
    private Telefono saveContactPhone(Establecimiento establecimiento, TelefonoDTO telefonoDTO) {
        Telefono contactPhone = Telefono.getBuilder(establecimiento,
                telefonoDTO.getPhoneType(),
                telefonoDTO.getPhoneNumber())
                .build();

        return telefonoRepository.save(contactPhone);
    }

    // endregion

}
