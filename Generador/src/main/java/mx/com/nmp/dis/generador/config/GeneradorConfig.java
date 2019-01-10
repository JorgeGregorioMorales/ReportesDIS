package mx.com.nmp.dis.generador.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan(basePackages = "mx.com.nmp.dis.generador")
@ComponentScan({"mx.com.nmp.dis.generador","mx.com.nmp.dis.util"})
@PropertySource(value = { "classpath:application.properties" })
public class GeneradorConfig {

}
