package mx.com.nmp.dis.almacenador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiModel;

/**
 * 
 * Punto de acceso para información del almacenador
 * 
 * @author jgregorio
 * @date 2019-01-09
 *
 */
@Controller
@RequestMapping("/")
@ApiModel(value = "AlmacenadorController", description = "Punto de acceso para información del almacenador")
public class AlmacenadorController {

    private static final String VERSION = "1.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(AlmacenadorController.class);

    /**
     * Versión del controlador
     * 
     * @return
     */
    @GetMapping("/version")
    @ResponseBody
    public String version() {
        LOGGER.debug("/AlmacenadorDIS/version GET => {}", VERSION);
        return VERSION;
    }
}
