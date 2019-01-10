package mx.com.nmp.dis.gestionador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiModel;

/**
 * 
 * Punto de acceso para información de gestionador
 * 
 * @author jgregorio
 * @date 2019-01-09
 *
 */
@Controller
@RequestMapping("/")
@ApiModel(value = "GestionadorController", description = "Punto de acceso para información de gestionador")
public class GestionadorController {

    private static final String VERSION = "1.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(GestionadorController.class);

    /**
     * Versión del controlador
     * 
     * @return
     */
    @GetMapping("/version")
    @ResponseBody
    public String version() {
        LOGGER.debug("/GestionadorDIS/version GET => {}", VERSION);
        return VERSION;
    }
}
