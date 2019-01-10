package mx.com.nmp.dis.gestionador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mx.com.nmp.dis.gestionador")
public class GestionadorMain {

    public static void main(String[] args) {
        SpringApplication.run(GestionadorMain.class, args);
    }
}
