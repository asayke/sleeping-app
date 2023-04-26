package ru.asayke.jwtappdemo.service;

import ru.asayke.jwtappdemo.models.User;

import java.util.List;

public interface UserService {
    void register(User user);
    List<User> getAll();
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);
    void delete(Long id);
    void save(User user);
    boolean existsByEmail(String email);
    void deleteById(Long id);
}