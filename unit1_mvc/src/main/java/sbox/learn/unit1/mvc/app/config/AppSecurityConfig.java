package sbox.learn.unit1.mvc.app.config;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import sbox.learn.unit1.mvc.app.services.AuthProvider;

/**
 * Конфигурация Spring Security
 */
@Log4j
@Configuration
@EnableWebSecurity
@ComponentScan
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("Configure auth provider");
        auth.authenticationProvider(new AuthProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Configure http security");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/auth")
                .defaultSuccessUrl("/books/shelf", true)
                .failureForwardUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info("Configure web security");
        web
                // срабатывает раньше конфигурации http
                .ignoring()
                // пропускаем все запросы к изображениям без аутентификации
                .antMatchers("/images/**");
    }
}
