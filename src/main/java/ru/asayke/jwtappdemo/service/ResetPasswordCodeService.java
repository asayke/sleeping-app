package ru.asayke.jwtappdemo.service;

import ru.asayke.jwtappdemo.models.ResetPasswordCode;
import ru.asayke.jwtappdemo.models.User;

import java.util.Optional;

public interface ResetPasswordCodeService {
    Optional<ResetPasswordCode> findByUser(User user);
    void save(ResetPasswordCode passwordCode);
    void delete(ResetPasswordCode passwordCode);
    boolean existByUser(User user);
    void deleteByUser(User user);
    Optional<ResetPasswordCode> findByResetCode(int code);
}