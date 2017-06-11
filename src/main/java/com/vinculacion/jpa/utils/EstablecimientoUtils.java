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

    /*
    // region Properties

    public static void printProperty(String header, String property) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println(property);
    }

    // endregion

    // region List Establecimientos

    public static void listEstablecimiento(String header, Establecimiento establecimiento) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        System.out.println(establecimiento);
        System.out.println();
    }

    public static void listEstablecimientos(String header, List<Establecimiento> establecimiento) {
        System.out.println("\r\n" + header + " ------------------------------ ");
        System.out.println();
        establecimiento.forEach(System.out::println);
        System.out.println();
    }

    public static void listContactWithDetail(Contact contact) {
        System.out.println("SINGLE CONTACT WITH DETAILS ---------------------------------");
        System.out.println();
        System.out.println(contact);
        if (contact.getContactPhones() != null) {
            contact.getContactPhones().forEach(System.out::println);
        }
        System.out.println();
    }

    public static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println("LISTING ENTITIES WITH DETAILS ---------------------------------");
        System.out.println();
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactPhones() != null) {
                contact.getContactPhones().forEach(System.out::println);
            }
            System.out.println();
        }
    }

    // endregion
*/
    // region Update Contacts, Phones and Hobbies

    public static List<EstablecimientoDTO> establecimientossToEstablecimientoDTOs(List<Establecimiento> establecimientos) {
        return establecimientos.stream().map(EstablecimientoUtils::establecimientoToEstablecimientoDTO).collect(Collectors.toList());
    }

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
        dto.setEstAfiliado(establecimiento.getEstAfiliado());
        dto.setCntId(establecimiento.getCanton());
        dto.setTipoEstId(establecimiento.getTipoEstablecimiento());
        if (establecimiento.getTelefonos() != null) {
            dto.setContactPhones(
                    establecimiento.getTelefonos().stream().map(TelefonoDTO::new).collect(Collectors.toSet()));
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
