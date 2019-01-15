package mx.com.nmp.dis.elastic.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class ElasticSearchConfiguration extends AbstractFactoryBean<RestHighLevelClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    @Value("${elasticsearch.cluster-name}")
    private String clusterNodes;

    @Value("${elasticsearch.cluster-nodes}")
    private String clusterName;

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.protocol}")
    private String protocol;

    @Value("${elasticsearch.port}")
    private int port;

    private RestHighLevelClient restHighLevelClient;

    @Override
    public void destroy() {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        LOGGER.info("HOST     = "+host);
        LOGGER.info("PORT     = "+port);
        LOGGER.info("PROTOCOL = "+protocol);
        
        try {
//            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, protocol)));
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(host, port, protocol)));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return restHighLevelClient;
    }

}
