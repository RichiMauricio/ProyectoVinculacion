package com.vinculacion.jpa.utils;

import com.vinculacion.jpa.dto.CorreoDTO;
import com.vinculacion.jpa.dto.EstablecimientoDTO;
import com.vinculacion.jpa.dto.TelefonoDTO;
import com.vinculacion.jpa.model.Establecimiento;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class EstablecimientoUtils {

    // region Update Establecimientos y teléfonos

    public static EstablecimientoDTO establecimientoToEstablecimientoDTO(Establecimiento establecimiento) {
        EstablecimientoDTO dto = new EstablecimientoDTO();

        dto.setEstablecimientoId(establecimiento.getEstId());
        dto.setEstNombre(establecimiento.getEstNombre());
        dto.setEstRepresentante(establecimiento.getEstRepresentante());
        dto.setEstDireccion(establecimiento.getEstDireccion());
        dto.setEstPagina(establecimiento.getEstPagina());
        dto.setEstLongitud(establecimiento.getEstLongitud());
        dto.setEstLatitud(establecimiento.getEstLatitud());
        dto.setEstFicheroImagenes(establecimiento.getEstFicheroImagenes());
        dto.setEstDescripcion(establecimiento.getEstDescripcion());
        dto.setEstAfiliado(establecimiento.getEstAfiliado());
        dto.setCntId(establecimiento.getCanton());
        dto.setTipoEstId(establecimiento.getTipoEstablecimiento());
        if (establecimiento.getTelefonos() != null) {
            dto.setContactPhones(
                    establecimiento.getTelefonos().stream().map(TelefonoDTO::new).collect(Collectors.toList()));
        }
        if (establecimiento.getCorreos() != null) {
            dto.setCorreosEst(
                    establecimiento.getCorreos().stream().map(CorreoDTO::new).collect(Collectors.toSet()));
        }
        return dto;
    }

    // endregion

    // region Random IDs

    public static Long randomEstablecimientoId() {
        Random rand = new Random();
        return (long) (rand.nextInt(10) + 1);
    }

    // endregion

}
