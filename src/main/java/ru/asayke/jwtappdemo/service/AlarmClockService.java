package ru.asayke.jwtappdemo.service;

import ru.asayke.jwtappdemo.dto.AlarmClockDTO;
import ru.asayke.jwtappdemo.models.AlarmClock;
import ru.asayke.jwtappdemo.models.User;

import java.util.Optional;

public interface AlarmClockService {
    Optional<AlarmClock> findByUser(User user);
    void save(AlarmClock clock);
    void resetClock(AlarmClockDTO alarmClockDTO, AlarmClock clock);
    boolean existsByUser(User user);
}