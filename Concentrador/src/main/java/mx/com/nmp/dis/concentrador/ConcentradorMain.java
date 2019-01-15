package mx.com.nmp.dis.concentrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "mx.com.nmp.dis.concentrador")
public class ConcentradorMain {

    public static void main(String[] args) {
        SpringApplication.run(ConcentradorMain.class, args);
    }
}
