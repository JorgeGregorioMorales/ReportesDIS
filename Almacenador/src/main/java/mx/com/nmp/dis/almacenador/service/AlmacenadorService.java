package mx.com.nmp.dis.almacenador.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.dis.elastic.dao.impl.SolicitudDaoImpl;
import mx.com.nmp.dis.elastic.model.Solicitud;
import mx.com.nmp.dis.enums.EstadoReporte;

@Service
public class AlmacenadorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlmacenadorService.class);

    @Autowired
    private SolicitudDaoImpl solicitudDao;

    public void executeAlmacenaReportes() {
        LOGGER.info("Buscando elementos con estado POR_ALMACENAR para procesar");

        List<Solicitud> solicitudes = solicitudDao.getByEstado(EstadoReporte.POR_ALMACENAR);

        LOGGER.info("Tamaño ids = {} ",solicitudes.size());
        List<String>  ids= new ArrayList<String>();
        
        for (Solicitud sol : solicitudes) {
            LOGGER.info("IDS {} ",sol.getId());
//            solicitudDao.updateEstado(sol.getId(), EstadoReporte.ALMACENANDO);
            ids.add(sol.getId());
        }
        solicitudDao.updateEstadoByIds(ids.toArray(new String[0]), EstadoReporte.ALMACENANDO);

        for (Solicitud sol : solicitudes) {
            LOGGER.info("Solicitud encontrada {} _ {}", sol.getId(), sol.getTipoReporte());
            solicitudDao.updateEstado(sol.getId(), EstadoReporte.POR_NOTIFICAR);
        }

    }

}
