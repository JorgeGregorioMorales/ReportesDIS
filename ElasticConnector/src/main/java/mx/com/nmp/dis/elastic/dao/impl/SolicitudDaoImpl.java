package mx.com.nmp.dis.elastic.dao.impl;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.nmp.dis.constantes.GenericFields;
import mx.com.nmp.dis.elastic.assembler.SolicitudAssembler;
import mx.com.nmp.dis.elastic.dao.GenericDao;
import mx.com.nmp.dis.elastic.model.Solicitud;
import mx.com.nmp.dis.enums.ErrorCode;
import mx.com.nmp.dis.enums.EstadoReporte;
import mx.com.nmp.dis.exception.BusinessServiceException;

@Repository
public class SolicitudDaoImpl implements GenericDao<Solicitud> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudDaoImpl.class);

    private final String INDEX = "solicitud";
    private final String TYPE  = "solicitud";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;

    public SolicitudDaoImpl(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper        = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SuppressWarnings("unchecked")
    public Solicitud insert(Solicitud solicitud) {
        solicitud.setId(UUID.randomUUID().toString());
        Map<String, Object> solicitudMap = objectMapper.convertValue(solicitud, Map.class);
        IndexRequest        indexRequest = new IndexRequest(INDEX, TYPE, solicitud.getId()).source(solicitudMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return solicitud;
    }

    public Solicitud getById(String id) {
        GetRequest  getRequest  = new GetRequest(INDEX, TYPE, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();

        return SolicitudAssembler.getSolicitud(sourceAsMap);
    }

    public Solicitud updateById(String id, Solicitud solicitud) {
        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true); // Fetch Object after its update
        try {
            String solicitudJson = objectMapper.writeValueAsString(solicitud);
            updateRequest.doc(solicitudJson, XContentType.JSON);
            UpdateResponse      updateResponse = restHighLevelClient.update(updateRequest);
            Map<String, Object> sourceAsMap    = updateResponse.getGetResult().sourceAsMap();
            return SolicitudAssembler.getSolicitud(sourceAsMap);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error al procesar la solicitud", e);
            throw new BusinessServiceException(ErrorCode.ERROR_PARSING_SOLICITUD);
        } catch (java.io.IOException e) {
            LOGGER.error("Error al procesar la solicitud", e);
            throw new BusinessServiceException(ErrorCode.ERROR_GENERICO);
        }
    }

    public Boolean updateEstado(String id, EstadoReporte estado) {
        LOGGER.debug("== Actualizando estado del documento={}  a {}", id, estado);
        Boolean result = Boolean.FALSE;
        try {

            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index(INDEX);
            updateRequest.type(TYPE);
            updateRequest.id(id);
            updateRequest.doc(jsonBuilder().startObject().field(GenericFields.FECHA_ACTUALIZACION, new Date()).field(GenericFields.ESTADO, estado).endObject());

            restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            LOGGER.debug("Se ha terminado de actualziar documento {}",id);
            result = Boolean.TRUE;
        } catch (JsonProcessingException e) {
            LOGGER.error("Error al procesar la solicitud", e);
            result = Boolean.FALSE;
        } catch (java.io.IOException e) {
            LOGGER.error("Error al procesar la solicitud", e);
            result = Boolean.FALSE;
        }
        return result;
    }

    public Boolean updateEstadoByIds(String[] ids, EstadoReporte estado) {

        String[]             indices       = new String[] { INDEX };
        UpdateByQueryRequest updateRequest = new UpdateByQueryRequest(indices);
        updateRequest.setQuery(QueryBuilders.idsQuery().addIds(ids).types(TYPE));

        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source.estado = '" + estado + "'", Collections.emptyMap());
        updateRequest.setScript(script);

        Boolean result = Boolean.FALSE;

        try {
            LOGGER.debug("** Actualizando estado del documento={}  a {}", ids, estado);

            BulkByScrollResponse bulkResponse = restHighLevelClient.updateByQuery(updateRequest, RequestOptions.DEFAULT);
            LOGGER.debug("** Se ha terminado de actualziar documento {}", bulkResponse);
            result = Boolean.TRUE;
        } catch (JsonProcessingException e) {
            LOGGER.error("Error al procesar la solicitud", e);
            result = Boolean.FALSE;
        } catch (java.io.IOException e) {
            LOGGER.error("Error al procesar la solicitud", e);
            result = Boolean.FALSE;
        }
        return result;
    }

    public void deleteBookById(String id) {
//        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
//        try {
//            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
//        } catch (java.io.IOException e) {
//            LOGGER.error("Error al eliminar la solicitud", e);
//            throw new BusinessServiceException(ErrorCode.ERROR_DELETING_ELEMENT);
//        }
    }

    public List<Solicitud> getByEstado(EstadoReporte estado) {
        List<Solicitud> solicitudes = new ArrayList<>();
        try {
            LOGGER.info("Buscando por estado ={}", estado);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchQuery(GenericFields.ESTADO, estado));
            sourceBuilder.from(0);
            sourceBuilder.size(10);
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices(INDEX);
            searchRequest.source(sourceBuilder);
            LOGGER.info("Query => {}", searchRequest);

            SearchResponse response = restHighLevelClient.search(searchRequest);

            solicitudes = SolicitudAssembler.getSolicitudes(response.getHits().getHits());

            LOGGER.info("Se encontraron {} elementos", solicitudes.size());
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        return solicitudes;

    }
}
