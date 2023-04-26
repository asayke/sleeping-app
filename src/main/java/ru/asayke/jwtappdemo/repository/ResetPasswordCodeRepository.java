package ru.asayke.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asayke.jwtappdemo.models.ResetPasswordCode;
import ru.asayke.jwtappdemo.models.User;

import java.util.Optional;

@Repository
public interface ResetPasswordCodeRepository extends JpaRepository<ResetPasswordCode, Long> {
    Optional<ResetPasswordCode> findByUser(User user);
    boolean existsByUser(User user);
    void deleteByUser(User user);
    Optional<ResetPasswordCode> findByResetCode(int code);
}