package mx.com.nmp.dis.elastic.assembler;

import static mx.com.nmp.dis.constantes.GenericFields.ESTADO;
import static mx.com.nmp.dis.constantes.GenericFields.ID;
import static mx.com.nmp.dis.constantes.GenericFields.PARAMETROS;
import static mx.com.nmp.dis.constantes.GenericFields.PROCENTAJE;
import static mx.com.nmp.dis.constantes.GenericFields.RUTA_ARCHIVO;
import static mx.com.nmp.dis.constantes.GenericFields.TIPO_REPORTE;
import static mx.com.nmp.dis.constantes.GenericFields.*;
import static mx.com.nmp.dis.elastic.util.FieldUtil.getLong;
import static mx.com.nmp.dis.elastic.util.FieldUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.nmp.dis.elastic.model.Solicitud;
import mx.com.nmp.dis.enums.EstadoReporte;
import mx.com.nmp.dis.enums.TipoReporte;

public final class SolicitudAssembler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudAssembler.class);

    @SuppressWarnings("unchecked")
    public static final Solicitud getSolicitud(Map<String, Object> fields) {
        Solicitud solicitud = new Solicitud();
        LOGGER.trace("Fields=> {}", fields);
        solicitud.setId(fields.get(ID).toString());
        solicitud.setEstado(EstadoReporte.valueOf(fields.get(ESTADO).toString()));
        solicitud.setTipoReporte(TipoReporte.valueOf(fields.get(TIPO_REPORTE).toString()));
        solicitud.setPorcentaje(Double.valueOf(fields.get(PROCENTAJE).toString()));
        solicitud.setRutaArchivo(getString(fields, RUTA_ARCHIVO));
        solicitud.setXml(getString(fields, XML));
//        solicitud.setFechaActualizacion(getDate(fields,FECHA_ACTUALIZACION));
        solicitud.setParametros(ParametroAssembler.getParametros((Map<String, Object>) fields.get(PARAMETROS)));
        return solicitud;
    }

    public static final List<Solicitud> getSolicitudes(SearchHit[] results) {
        List<Solicitud> solicitudes = new ArrayList<>();

        if (results != null && results.length > 0) {
            LOGGER.trace("Total elementos encontrados =>{}", results.length);
            for (SearchHit result : results) {
                solicitudes.add(getSolicitud(result.getSourceAsMap()));
            }
        }
        return solicitudes;
    }
}
