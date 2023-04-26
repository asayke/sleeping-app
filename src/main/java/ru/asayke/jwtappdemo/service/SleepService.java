package ru.asayke.jwtappdemo.service;

import ru.asayke.jwtappdemo.models.Sleep;
import ru.asayke.jwtappdemo.models.User;

import java.util.List;

public interface SleepService {
    void addSleep(Sleep sleep, String username);
    List<Sleep> findAllByUser(User user);
    Sleep findLastUserSleep(User user);
}