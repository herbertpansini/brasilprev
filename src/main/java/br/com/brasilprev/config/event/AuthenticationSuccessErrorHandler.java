package br.com.brasilprev.config.event;

import br.com.brasilprev.model.User;
import br.com.brasilprev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private static final String CLIENT_ID = "frontendapp";

    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    private final UserService userService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if (authentication.getName().equalsIgnoreCase(CLIENT_ID)) {
            return;
        }

        User user = this.userService.findByUsername(authentication.getName());
        String mensaje = "Success login: " + user.getUsername();
        System.out.println(mensaje);
        log.info(mensaje);
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String mensaje = "Error login: " + exception.getMessage();
        System.out.println(mensaje);
        log.error(mensaje);
    }
}