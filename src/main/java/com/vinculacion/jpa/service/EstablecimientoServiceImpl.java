package com.vinculacion.jpa.service;

import com.google.common.collect.Lists;
import com.vinculacion.jpa.dto.CorreoDTO;
import com.vinculacion.jpa.dto.EstablecimientoDTO;
import com.vinculacion.jpa.dto.TelefonoDTO;
import com.vinculacion.jpa.exceptions.EstablecimientoNotFoundException;
import com.vinculacion.jpa.model.*;
import com.vinculacion.jpa.model.validators.ExtendedEmailValidator;
import com.vinculacion.jpa.repository.CorreoRepository;
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

    @Autowired
    private CorreoRepository correoRepository;

    @PersistenceContext
    private EntityManager em;

    // endregion

    // region Establecimientos ------------------------- */

    @Transactional(readOnly = true)
    public List<Establecimiento> findAll() {
        return Lists.newArrayList(establecimientoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> searchByNombre(String nombre) {
        return establecimientoRepository.findByestNombreIgnoreCaseContains(nombre);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> getEstablecimientosWithDetail() {
        return establecimientoRepository.findAllWithDetail();
    }

    @Transactional(readOnly = true)
    public Establecimiento getEstablecimientoByIdWithDetail(int ID) {
        return establecimientoRepository.findByContactIdWithDetail(ID);
    }

    @Transactional(readOnly = true)
    public Establecimiento findEstablecimientoById(int ID) throws EstablecimientoNotFoundException {

        logger.debug("Finding contact by id: {}", ID);

        Establecimiento found = establecimientoRepository.findOne(ID);

        if (found == null) {
            logger.info("No contact found with id: {}", ID);
            throw new EstablecimientoNotFoundException("No contact found with id: " + ID);
        }

        return found;
    }


    @Transactional(readOnly = true)
    public List<Establecimiento> getEstablecimientoByNombre(String estNombre) {
        return establecimientoRepository.findByEstNombre(estNombre);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> getEstablecimientoByNombreCntTip(String estNombre, int canton, int tipoEst) {
        return establecimientoRepository.findByEstNombreCntTip(estNombre, canton, tipoEst);
    }

    @Transactional(readOnly = true)
    public List<Establecimiento> getEstablecimientoByRepresentante(String estRepresentante) {
        return establecimientoRepository.findByestRepresentante(estRepresentante);
    }

    @Override
    public List<Establecimiento> getEstablecimientoByCantonyTipo(int cntId, int tipoEst) {
        return establecimientoRepository.findByCantonyTipo(cntId, tipoEst);
    }

    @Override
    public List<Establecimiento> getEstablecimientoByParams(String estRepresentante, int canton) {
        return establecimientoRepository.findByParams(estRepresentante, canton);
    }

    /*//Servicio para obtener todos los establecimientos según el canton
    @Override
    public List<Establecimiento> getEstablecimientosByCanton(Canton cantonId,TipoEstablecimiento tipoEstablecimiento) {
        return establecimientoRepository.findByCantonAllEstab(cantonId,tipoEstablecimiento);
    }*/

    @Transactional(rollbackFor = EstablecimientoNotFoundException.class)
    @Override
    public Establecimiento deleteById(int id) throws EstablecimientoNotFoundException {
        Establecimiento deleted = findEstablecimientoById(id);
        establecimientoRepository.delete(deleted);
        return deleted;
    }

    @Transactional(rollbackFor = EstablecimientoNotFoundException.class)
    @Override
    public Establecimiento update(EstablecimientoDTO establecimientoDTO) throws EstablecimientoNotFoundException {
        Establecimiento found = findEstablecimientoById(establecimientoDTO.getEstablecimientoId());
        // Update the Establecimiento information
        found.update(establecimientoDTO.getEstNombre(), establecimientoDTO.getEstRepresentante(),
                establecimientoDTO.getEstDireccion(), establecimientoDTO.getEstPagina(), establecimientoDTO.getEstLongitud(),
                establecimientoDTO.getEstLatitud(), establecimientoDTO.getEstFicheroImagenes(),establecimientoDTO.getEstDescripcion(),
                establecimientoDTO.getEstAfiliado());
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

            if (establecimientoDTO.isUpdateChildren()) {
                if (establecimientoDTO.getCorreosEst() != null) {
                    for (CorreoDTO correoDTO : establecimientoDTO.getCorreosEst()) {
                        Correo correo = correoRepository.findBycorreoId(correoDTO.getCorreoId());
                        if (correo != null) {
                            correo.update(correoDTO.getCorreoNombre());
                        } else {
                            correo = saveEstablecimientoCorreo(found, correoDTO);
                            found.getCorreos().add(correo);
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
        Establecimiento establecimiento = Establecimiento.getBuilder(
                establecimientoDTO.getTipoEstId(),
                establecimientoDTO.getCntId(),
                establecimientoDTO.getEstNombre(),
                establecimientoDTO.getEstRepresentante(),
                establecimientoDTO.getEstDireccion(),
                establecimientoDTO.getEstPagina(),
                establecimientoDTO.getEstLongitud(),
                establecimientoDTO.getEstLatitud(),
                establecimientoDTO.getEstFicheroImagenes(),
                establecimientoDTO.getEstDescripcion(),
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
    public Telefono deleteEstablecimientoPhoneById(int contactPhoneId) throws EstablecimientoNotFoundException {

        Telefono contactPhone = telefonoRepository.findOne(contactPhoneId);
        if (contactPhone != null) {
            logger.info("Quitando el teléfono del establecimiento con información: {}", contactPhone);
            telefonoRepository.delete(contactPhone);
        }

        return contactPhone;
    }

    @Override
    public Correo deleteCorreoEstablecimientoById(int correoId) throws EstablecimientoNotFoundException {
        Correo correo = correoRepository.findOne(correoId);
        if (correo != null) {
            logger.info("Quitando el correo del establecimiento con información: {}", correo);
            correoRepository.delete(correo);
        }

        return correo;
    }

    @Transactional(readOnly = true)
    public List<Telefono> findContactPhonesByestId(int contactId) {
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
    public Telefono findContactPhoneById(int contactPhoneID) {
        return telefonoRepository.findBytlfId(contactPhoneID);
    }

    // endregion

    // region privada para guardar los correos y teléfonos
    @Transactional
    private Telefono saveContactPhone(Establecimiento establecimiento, TelefonoDTO telefonoDTO) {
        Telefono contactPhone = Telefono.getBuilder(establecimiento,
                telefonoDTO.getPhoneType(),
                telefonoDTO.getPhoneNumber())
                .build();

        return telefonoRepository.save(contactPhone);
    }

    @Transactional
    private Correo saveEstablecimientoCorreo(Establecimiento establecimiento, CorreoDTO correoDTO) {
        Correo establecimientoCorreo = Correo.getBuilder(establecimiento,
                correoDTO.getCorreoNombre())
                .build();

        return correoRepository.save(establecimientoCorreo);
    }

    // endregion

}
