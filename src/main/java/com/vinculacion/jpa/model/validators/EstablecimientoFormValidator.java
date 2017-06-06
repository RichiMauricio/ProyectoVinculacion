package com.vinculacion.jpa.model.validators;

import com.vinculacion.jpa.model.Establecimiento;
import com.vinculacion.jpa.model.Telefono;
import com.vinculacion.jpa.service.EstablecimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Mauricio on 05/06/2017.
 */
@Component
public class EstablecimientoFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(EstablecimientoFormValidator.class);

    private final EstablecimientoService establecimientoService;
    //Corregir
    @Autowired
    public EstablecimientoFormValidator(EstablecimientoService establecimientoService) {
        this.establecimientoService = establecimientoService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Establecimiento.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        logger.debug("Validando {}", target);
        Establecimiento form = (Establecimiento) target;
        if (!form.isNew()) {
            validateContactPhones(errors, form);
        }
    }

    private void validateContactPhones(Errors errors, Establecimiento form) {
        for (Telefono contactPhone : form.getTelefonos())
            if (contactPhone.getTlfNumero().isEmpty() ||
                    contactPhone.getTlfTipo().isEmpty()) {
                errors.reject("NoVacio.establecimiento.establecimientoPhone.campo");
                break;
            }
    }
}
