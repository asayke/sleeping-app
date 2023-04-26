package ru.asayke.jwtappdemo.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserExceptionResponse {
    private String message;
    private long timestamp;
}