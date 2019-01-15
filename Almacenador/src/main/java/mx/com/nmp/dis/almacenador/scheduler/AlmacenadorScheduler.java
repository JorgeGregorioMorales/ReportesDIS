package mx.com.nmp.dis.almacenador.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mx.com.nmp.dis.almacenador.service.AlmacenadorService;

@Component
public class AlmacenadorScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlmacenadorScheduler.class);

    @Autowired
    private AlmacenadorService almacenadorService;

    @Scheduled(fixedRate = 10000)
    public void excecuteScheduler() {
        LOGGER.info("Ejecutando scheduler del almacenador ..");
        almacenadorService.executeAlmacenaReportes();
    }
}