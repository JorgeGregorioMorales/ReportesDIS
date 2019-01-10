package mx.com.nmp.dis.generador.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mx.com.nmp.dis.util.FileUtil;

@Component
public class GeneradorScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneradorScheduler.class);

    @Autowired
    private FileUtil fileUtil;

    @Scheduled(fixedRate = 5000)
    public void excecuteGetPendingFiles() {
        Boolean existeGenerador= fileUtil.executeExistFile("/logs/Generador.log");
        Boolean existeGenerador2= fileUtil.executeExistFile("/logs/Generador2.log");
        LOGGER.info("Resultados de la busqueda de archivos ExisteGenerador={}  ExisteGenerador2={}",existeGenerador,existeGenerador2);
    }
}