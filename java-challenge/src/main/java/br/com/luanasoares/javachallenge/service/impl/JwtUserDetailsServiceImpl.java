package br.com.luanasoares.javachallenge.service.impl;

import br.com.luanasoares.javachallenge.model.User;
import br.com.luanasoares.javachallenge.security.JwtUserFactory;
import br.com.luanasoares.javachallenge.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.findByUsername(username);
        if (user != null) {
            return JwtUserFactory.create(user);
        }

        throw new UsernameNotFoundException("Usename not found.");
    }

}
