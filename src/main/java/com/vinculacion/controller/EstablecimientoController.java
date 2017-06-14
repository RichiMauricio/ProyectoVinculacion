package com.vinculacion.controller;

import com.vinculacion.jpa.dto.EstablecimientoDTO;
import com.vinculacion.jpa.exceptions.EstablecimientoNotFoundException;
import com.vinculacion.jpa.model.*;
import com.vinculacion.jpa.model.validators.EstablecimientoFormValidator;
import com.vinculacion.jpa.service.CantonService;
import com.vinculacion.jpa.service.EstablecimientoService;
import com.vinculacion.jpa.service.TipoEstablecimientoServiceImpl;
import com.vinculacion.jpa.utils.EstablecimientoUtils;
import com.vinculacion.jpa.utils.SharedUtils;
import jdk.nashorn.internal.runtime.Debug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Mauricio on 26/04/2017.
 */
@Controller
@SessionAttributes("establecimiento")
public class EstablecimientoController {

    private EstablecimientoService establecimientoService;
    private CantonService cantonService;
    private TipoEstablecimientoServiceImpl tipoEstablecimientoService;
    private EstablecimientoFormValidator establecimientoFormValidator;
    protected static final String MODEL_ATTRIBUTE_CANTONES = "cantones";
    protected static final String MODEL_ATTRIBUTE_TIPOS_ESTABL = "tipoEstablecimiento";
    protected static final String MODEL_ATTRIBUTE_ESTABLECIMIENTO = "establecimiento";
    protected static final String MODEL_ATTRIBUTE_ESTABLECIMIENTOS = "establecimientos";
    protected static final String PARAMETER_ESTABLECIMIENTO_ID = "id";

    private static final Logger logger = LoggerFactory.getLogger(Establecimiento.class);

    protected static final String ESTABLECIMIENTO_VIEW = "establecimientos/view";
    protected static final String ESTABLECIMIENTO_LIST_VIEW = "establecimientos/list";
    protected static final String ESTABLECIMIENTO_FORM_VIEW = "establecimientos/establecimientoform";
    protected static final String SEARCH_VIEW = "establecimientos/search";

    //Constructor
    @Autowired
    public EstablecimientoController(EstablecimientoService establecimientoService, CantonService cantonService, TipoEstablecimientoServiceImpl tipoEstablecimientoService, EstablecimientoFormValidator establecimientoFormValidator) {
        this.establecimientoService = establecimientoService;
        this.cantonService = cantonService;
        this.tipoEstablecimientoService = tipoEstablecimientoService;
        this.establecimientoFormValidator = establecimientoFormValidator;
    }

    @InitBinder("establecimiento")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(establecimientoFormValidator);
        binder.setDisallowedFields("id");
    }

    //-------MODEL ATRIBUTES---------

    //Lista de los establecimientos
    @ModelAttribute(MODEL_ATTRIBUTE_ESTABLECIMIENTOS)
    public List<Establecimiento> allEstablecimientos() {
        return establecimientoService.findAll();
    }

    //Lista de los cantones para el establecimiento
    @ModelAttribute(MODEL_ATTRIBUTE_CANTONES)
    public List<Canton> allCantones() {
        return cantonService.findAll();
    }

    //Lista de los tipos de establecimiento para el establecimiento
    @ModelAttribute(MODEL_ATTRIBUTE_TIPOS_ESTABL)
    public List<TipoEstablecimiento> allTiposEstab() {
        return tipoEstablecimientoService.findAll();
    }

    //-------FIN MODEL ATRIBUTES---------

    //---------MAPPINGS------------
    //Agregar nuevo establecimiento
    @RequestMapping(value = "/establecimiento/new", method = RequestMethod.GET)
    public String initAddEstablecimientoForm(Model model) {
        try{
            Establecimiento establecimiento = new Establecimiento();
            model.addAttribute(establecimiento);
            return ESTABLECIMIENTO_FORM_VIEW;
        }catch(Exception e){
            logger.debug(e.getMessage());
            return "falló";
        }
    }

    @RequestMapping(value = "/establecimiento/new", method = RequestMethod.POST)
    public String addEstablecimiento(@Valid Establecimiento establecimiento, BindingResult result, SessionStatus status,
                             RedirectAttributes attributes) {
        Collection<Establecimiento> yaExiste = null;
        yaExiste = establecimientoService.getEstablecimientoByNombre(establecimiento.getEstNombre());
        if (result.hasErrors()||(yaExiste.size()>1)) {
            result.rejectValue("estNombre", "establecimiento.agregacion.noAgregada", new Object[] { establecimiento.getEstNombre() },
                    "ya existe un establecimiento con ese nombre");
            return ESTABLECIMIENTO_FORM_VIEW;
        } else {
            EstablecimientoDTO establecimientoDTO = EstablecimientoUtils.establecimientoToEstablecimientoDTO(establecimiento);
            Establecimiento added = establecimientoService.add(establecimientoDTO);
            logger.info("Establecimiento agregado con la siguiente información: {}", added);
            status.setComplete();
            return "redirect:/establecimiento/update/"+added.getEstId();
        }
    }

    //Mostrar un establecimiento
    @RequestMapping(value = "/establecimiento/{establecimientotId}", method = GET)
    public String establecimientoDisplayPage(@PathVariable("establecimientotId") Long id, Model model) throws EstablecimientoNotFoundException {
        logger.debug("Mostrando página de establecimiento con id: {}", id);

        Establecimiento found = establecimientoService.findEstablecimientoById(id);
        logger.debug("Establecimiento encontrado: {}", found);

        model.addAttribute(MODEL_ATTRIBUTE_ESTABLECIMIENTO, found);
        return ESTABLECIMIENTO_VIEW;
    }

    //Actualizar un establecimiento
    @RequestMapping(value = "/establecimiento/update/{establecimientoId}", method = GET)
    public String establecimientoEditPage(@PathVariable("establecimientoId") Long id, Model model) throws EstablecimientoNotFoundException {
        Establecimiento found = establecimientoService.getEstablecimientoByIdWithDetail(id);
        model.addAttribute(MODEL_ATTRIBUTE_ESTABLECIMIENTO, found);
        return ESTABLECIMIENTO_FORM_VIEW;
    }

    //Actualizar teléfonos
    @RequestMapping(value = "/establecimiento/update/{establecimientoId}", params = { "addEstablecimientoPhone" }, method = RequestMethod.POST)
    public String addPhoneRow(final Establecimiento establecimiento) {
        Telefono establecimientoPhone = Telefono.getBuilder(establecimiento, null, null).build();
        establecimientoPhone.setTlfId(SharedUtils.randomNegativeId());
        establecimiento.getTelefonos().add(establecimientoPhone);
        return ESTABLECIMIENTO_FORM_VIEW;
    }

    //Actualizar correos
    @RequestMapping(value = "/establecimiento/update/{establecimientoId}", params = { "addCorreoEstablecimiento" }, method = RequestMethod.POST)
    public String addCorreoRow(final Establecimiento establecimiento) {
        Correo correo = Correo.getBuilder(establecimiento,null).build();
        correo.setCorreoId(SharedUtils.randomNegativeId());
        establecimiento.getCorreos().add(correo);
        return ESTABLECIMIENTO_FORM_VIEW;
    }

    //Eliminar un teléfono
    @RequestMapping(value = "/establecimiento/update/{establecimientoId}", params = {
            "removeEstablecimientoPhone"}, method = RequestMethod.POST)
    public String removePhoneRow(final Establecimiento establecimiento, final HttpServletRequest req) throws EstablecimientoNotFoundException {
        final Long establecimientoPhoneId = Long.valueOf(req.getParameter("removeEstablecimientoPhone"));
        for (Telefono establecimientoPhone : establecimiento.getTelefonos()) {
            if (establecimientoPhone.getTlfId().equals(establecimientoPhoneId)) {
                establecimiento.getTelefonos().remove(establecimientoPhone);
                break;
            }
        }
        if (establecimientoPhoneId > 0)
            establecimientoService.deleteEstablecimientoPhoneById(establecimientoPhoneId);

        return ESTABLECIMIENTO_FORM_VIEW;
    }

    //Eliminar un correo
    @RequestMapping(value = "/establecimiento/update/{establecimientoId}", params = {
            "removeCorreoEstablecimiento"}, method = RequestMethod.POST)
    public String removeCorreoRow(final Establecimiento establecimiento, final HttpServletRequest req) throws EstablecimientoNotFoundException {
        final Long correoEstablecimientoId = Long.valueOf(req.getParameter("removeCorreoEstablecimiento"));
        for (Correo correo : establecimiento.getCorreos()) {
            if (correo.getCorreoId().equals(correoEstablecimientoId)) {
                establecimiento.getCorreos().remove(correo);
                break;
            }
        }
        if (correoEstablecimientoId > 0)
            establecimientoService.deleteCorreoEstablecimientoById(correoEstablecimientoId);
        return ESTABLECIMIENTO_FORM_VIEW;
    }

    //Guardar cambios del establecimiento
    @RequestMapping(value = "/establecimiento/update/{establecimientoId}", method = RequestMethod.POST)
    public String updateEstablecimiento(@Valid @ModelAttribute("establecimiento") Establecimiento establecimiento, BindingResult result,
                                RedirectAttributes attributes, Model model) throws EstablecimientoNotFoundException {
        try{
            if (result.hasErrors()) {
                return ESTABLECIMIENTO_FORM_VIEW;
            } else {

                EstablecimientoDTO establecimientoDTO = EstablecimientoUtils.establecimientoToEstablecimientoDTO(establecimiento);
                establecimientoDTO.setUpdateChildren(true);
                this.establecimientoService.update(establecimientoDTO);
                attributes.addAttribute(PARAMETER_ESTABLECIMIENTO_ID, establecimientoDTO.getEstablecimientoId());

                return "redirect:/establecimientos";
            }
        }catch (Exception e){
            return ESTABLECIMIENTO_FORM_VIEW;
        }
    }

    //Mostrar todos los establecimientos
    @RequestMapping(value = "/establecimientos", method = GET)
    public String showEstablecimientosPage(Model model) {
        logger.debug("Mostrando toda la página de establecimientos");
        return ESTABLECIMIENTO_LIST_VIEW;
    }

    //Eliminar un establecimiento
    @RequestMapping(value = "/establecimiento/delete/{establecimientoId}", method = GET)
    public String delete(@PathVariable("establecimientoId") Long estId) throws EstablecimientoNotFoundException {
        establecimientoService.deleteById(estId);
        return "redirect:/establecimientos/list";
    }

    //Buscar un estbalecimiento
    @RequestMapping(value = "/establecimientos/search", method = GET)
    public String search(Model model, HttpServletRequest request) {
        model.addAttribute(MODEL_ATTRIBUTE_ESTABLECIMIENTO, new Establecimiento());
        return SEARCH_VIEW;
    }

    //Resultado de la búsqueda de un establecimiento
    @RequestMapping(value = "/establecimientos/list", method = RequestMethod.GET)
    public String processFindForm(Establecimiento establecimiento, BindingResult result, Model model, HttpSession session) {
        Collection<Establecimiento> results = null;
        int cntId = Integer.parseInt(String.valueOf(establecimiento.getCanton()));
        int tipoEstId = Integer.parseInt(String.valueOf(establecimiento.getTipoEstablecimiento()));
        try{
            //Verificar que no esté vacío el formulario
            if (StringUtils.isEmpty(establecimiento.getEstNombre())  && (StringUtils.isEmpty(establecimiento.getEstRepresentante()))) {
                results = this.establecimientoService.getEstablecimientoByCantonyTipo(cntId,tipoEstId);
            } else if(StringUtils.isEmpty(establecimiento.getEstRepresentante())){
                results = this.establecimientoService.getEstablecimientoByNombre(establecimiento.getEstNombre());
            }else{
                results = this.establecimientoService.getEstablecimientoByRepresentante(establecimiento.getEstRepresentante());
            }
            if (results.size() < 1) {
                // establecimientos no encontrados
                result.rejectValue("estNombre", "establecimiento.busqueda.noEncontrada", new Object[] { establecimiento.getEstNombre() },
                        "no escontrado");
                return SEARCH_VIEW;
            }
            session.setAttribute("buscarNombre", establecimiento.getEstNombre());
            if (results.size() > 1) {
                // múltiples establecimientos encontrados
                model.addAttribute(MODEL_ATTRIBUTE_ESTABLECIMIENTOS, results);
                return ESTABLECIMIENTO_LIST_VIEW;
            } else {
                // 1 establecimiento encontrado
                establecimiento = results.iterator().next();
                return "redirect:/establecimiento/" + establecimiento.getEstId();
            }
        }catch (Exception e){
            logger.debug("Excepción encontrada: " + e.getMessage());
            return "valimos";
        }
    }
    //---------FIN MAPPINGS------------
}