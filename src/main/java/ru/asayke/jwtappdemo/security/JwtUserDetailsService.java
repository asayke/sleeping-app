package ru.asayke.jwtappdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.security.jwt.JwtUserFactory;
import ru.asayke.jwtappdemo.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);

        if(user == null)
            throw new UsernameNotFoundException("User with username: " + s + " not found");

        return JwtUserFactory.create(user);
    }
}