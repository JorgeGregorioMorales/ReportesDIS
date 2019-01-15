package mx.com.nmp.dis.almacenador.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"mx.com.nmp.dis.almacenador","mx.com.nmp.dis.util","mx.com.nmp.dis.elastic.config","mx.com.nmp.dis.elastic.dao.impl"})
@PropertySource(value = { "classpath:application.properties" })
public class AlmacenadorConfig {

}
