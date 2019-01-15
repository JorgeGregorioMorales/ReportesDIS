package mx.com.nmp.dis.almacenador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "mx.com.nmp.dis.almacenador")
public class AlmacenadorMain {

    public static void main(String[] args) {
        SpringApplication.run(AlmacenadorMain.class, args);
    }
}
