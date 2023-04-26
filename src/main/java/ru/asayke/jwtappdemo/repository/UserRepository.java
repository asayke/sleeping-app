package ru.asayke.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asayke.jwtappdemo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteById(long id);
}