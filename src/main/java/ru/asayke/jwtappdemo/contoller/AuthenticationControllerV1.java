package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.asayke.jwtappdemo.dto.AuthenticationRequestDTO;
import ru.asayke.jwtappdemo.dto.EmailAuthRequestDTO;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.security.jwt.JwtTokenProvider;
import ru.asayke.jwtappdemo.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
@AllArgsConstructor
public class AuthenticationControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            String username = requestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));
            User user = userService.findByUsername(username);

            if(user == null)
                throw new UsernameNotFoundException("User with username: " + username + " not found");

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("email-login")
    public ResponseEntity emailLogin(@RequestBody EmailAuthRequestDTO emailAuthDTO) {
        try {
            String email = emailAuthDTO.getEmail();
            String username = userService.findByEmail(email).getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, emailAuthDTO.getPassword()));
            User user = userService.findByUsername(username);

            if(user == null)
                throw new UsernameNotFoundException("User with username: " + username + " not found");

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}