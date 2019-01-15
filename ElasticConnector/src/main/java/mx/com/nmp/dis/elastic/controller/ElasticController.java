package mx.com.nmp.dis.elastic.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiModel;
import mx.com.nmp.dis.elastic.dao.impl.SolicitudDaoImpl;
import mx.com.nmp.dis.elastic.model.Parametros;
import mx.com.nmp.dis.elastic.model.Solicitud;
import mx.com.nmp.dis.enums.EstadoReporte;
import mx.com.nmp.dis.enums.TipoReporte;

/**
 * 
 * Punto de acceso para información de concentrador
 * 
 * @author jgregorio
 * @date 2019-01-09
 *
 */
@Controller
@RequestMapping("/")
@ApiModel(value = "ElasticController", description = "Punto de acceso para información de concentrador")
public class ElasticController {

    private static final String VERSION = "1.0";

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticController.class);

    @Autowired
    private SolicitudDaoImpl solicitudDao;

    /**
     * Versión del controlador
     * 
     * @return
     */
    @GetMapping("/version")
    @ResponseBody
    public String version() {
        LOGGER.debug("/ElasticDIS/version GET => {}", VERSION);
        return VERSION;
    }

    @GetMapping("/insert")
    @ResponseBody
    public Solicitud insert() {
        Solicitud sol = new Solicitud();
        sol.setEstado(EstadoReporte.POR_ALMACENAR);
        sol.setTipoReporte(TipoReporte.PROYECTO_RUBROS);
        sol.setPorcentaje(0.0);
        sol.setFechaActualizacion(new Date());
        Parametros parametros = new Parametros();
        parametros.setIdCorrida(1041L);
        List<Long> idsDonatarias = new ArrayList<>();
        idsDonatarias.add(1051L);
        idsDonatarias.add(1341L);
        idsDonatarias.add(1611L);
        parametros.setIdsDonatarias(idsDonatarias);
        sol.setParametros(parametros);
        sol = solicitudDao.insert(sol);
        return sol;
    }

    @GetMapping("/update")
    @ResponseBody
    public String update() {
        List<String> ids = new ArrayList<>();
        ids.add("70ec9e6c-8c99-46c9-976e-a3775e3e3bf5");
        ids.add("60e78454-1a68-47c5-bed9-02234dd507fe");
        ids.add("460339b5-9028-4bdf-a65d-bf2be26b6593");
        ids.add("ceeb7937-c419-4e12-9c5a-654e614f00a5");
        ids.add("62d82be7-49ff-47f9-b1df-567bd5cefe8a");

        solicitudDao.updateEstadoByIds(ids.toArray(new String[0]), EstadoReporte.POR_ALMACENAR);

        return "true";
    }

    @GetMapping("/update/{estado}/{id}")
    @ResponseBody
    public Solicitud getEstado(@PathVariable String estado, @PathVariable String id) {
        LOGGER.debug("/update/ GET => {} - {}", estado, id);

        solicitudDao.updateEstado(id, EstadoReporte.valueOf( estado ) );

        return null;
    }
}
