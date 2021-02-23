package sbox.learn.unit1.mvc.app.services;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import sbox.learn.unit1.mvc.app.repositories.AccountRepository;

import java.util.ArrayList;

/**
 * Собственный провайдер аутентификации
 */
@Component
@Log4j
public class AuthProvider implements AuthenticationProvider {
    // @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("Authinticating "+name);
        if (true /*loginService.authenticate(name,password)*/) {
            // use the credentials
            // and authenticate against the third-party system
            log.info("Authinticated");
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            log.info("Authintication failed");
            throw new BadCredentialsException("Bad password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

