package br.com.brasilprev.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.brasilprev.model.User;
import br.com.brasilprev.repository.UserRepository;
import br.com.brasilprev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try	{
            User user = this.findByUsername(username);

            List<GrantedAuthority> authorities = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .peek(authority -> log.info("Role: " + authority.getAuthority()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails
                    .User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        } catch(Exception e) {
            String error = String.format("Username %s not found", username);
            log.error(error);
            throw new UsernameNotFoundException(error);
        }
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
