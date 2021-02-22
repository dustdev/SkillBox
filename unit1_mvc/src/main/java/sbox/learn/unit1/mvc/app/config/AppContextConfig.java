package sbox.learn.unit1.mvc.app.config;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Root контекста
 */
@Log4j
@Configuration
@ComponentScan("sbox.learn.unit1.mvc.app")
public class AppContextConfig {

}
