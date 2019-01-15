package mx.com.nmp.dis.elastic.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "mx.com.nmp.dis.elastic")
public class ElasticMain {

    public static void main(String[] args) {
        SpringApplication.run(ElasticMain.class, args);
    }
}
