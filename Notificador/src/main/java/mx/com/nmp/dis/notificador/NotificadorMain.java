package mx.com.nmp.dis.notificador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mx.com.nmp.dis.notificador")
public class NotificadorMain {

    public static void main(String[] args) {
        SpringApplication.run(NotificadorMain.class, args);
    }
}
