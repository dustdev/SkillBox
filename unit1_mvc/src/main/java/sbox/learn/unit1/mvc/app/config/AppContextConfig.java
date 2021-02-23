package sbox.learn.unit1.mvc.app.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Конфигурация Root контекста
 */
@Log4j
@Configuration
@ComponentScan("sbox.learn.unit1.mvc.app")
public class AppContextConfig {

}
