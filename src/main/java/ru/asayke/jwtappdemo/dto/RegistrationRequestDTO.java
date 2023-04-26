package ru.asayke.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequestDTO {
    private final String username;
    private final String fullName;
    private final String gender;
    private final String email;
    private final String password;
}