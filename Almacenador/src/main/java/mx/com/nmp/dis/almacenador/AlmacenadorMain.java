package mx.com.nmp.dis.almacenador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mx.com.nmp.dis.almacenador")
public class AlmacenadorMain {

    public static void main(String[] args) {
        SpringApplication.run(AlmacenadorMain.class, args);
    }
}
