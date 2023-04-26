package ru.asayke.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asayke.jwtappdemo.models.AlarmClock;
import ru.asayke.jwtappdemo.models.User;

import java.util.Optional;

public interface AlarmClockRepository extends JpaRepository<AlarmClock, Long> {
    Optional<AlarmClock> findByUser(User user);
    boolean existsByUser(User user);
}