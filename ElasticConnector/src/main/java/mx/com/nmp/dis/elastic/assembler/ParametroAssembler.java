package mx.com.nmp.dis.elastic.assembler;

import static mx.com.nmp.dis.constantes.GenericFields.IDS_DONATARIAS;
import static mx.com.nmp.dis.constantes.GenericFields.IDS_GRUPOS;
import static mx.com.nmp.dis.constantes.GenericFields.IDS_INDICADORES;
import static mx.com.nmp.dis.constantes.GenericFields.ID_CORRIDA;
import static mx.com.nmp.dis.constantes.GenericFields.ID_TIPO_INTEGRANTE;
import static mx.com.nmp.dis.elastic.util.FieldUtil.getListLong;
import static mx.com.nmp.dis.elastic.util.FieldUtil.getLong;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.nmp.dis.elastic.model.Parametros;

public final class ParametroAssembler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParametroAssembler.class);

    public static final Parametros getParametros(Map<String, Object> fields) {
        Parametros parametros = new Parametros();
        LOGGER.trace("Fields=> {}", fields);
        parametros.setIdCorrida(getLong(fields, ID_CORRIDA));
        parametros.setIdsDonatarias(getListLong(fields, IDS_DONATARIAS));
        parametros.setIdsIndicadores(getListLong(fields, IDS_INDICADORES));
        parametros.setIdsGrupos(getListLong(fields, IDS_GRUPOS));
        parametros.setIdTipoIntegrante(getLong(fields, ID_TIPO_INTEGRANTE));
        return parametros;
    }

}
