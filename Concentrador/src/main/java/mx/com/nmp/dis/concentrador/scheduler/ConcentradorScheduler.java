package mx.com.nmp.dis.concentrador.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConcentradorScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcentradorScheduler.class);

    @Scheduled(fixedRate = 10000)
    public void excecuteGetPendingFiles() {
        LOGGER.info("Buscando elementos a procesar ...");
    }
}