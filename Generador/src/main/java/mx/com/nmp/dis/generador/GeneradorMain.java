package mx.com.nmp.dis.generador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeneradorMain {

    public static void main(String[] args) {
        SpringApplication.run(GeneradorMain.class, args);
    }
}
