package ru.asayke.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asayke.jwtappdemo.models.Sleep;
import ru.asayke.jwtappdemo.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
    Optional<Sleep> findById(Long id);
    List<Sleep> findAllByUser(User user);
    Optional<Sleep> findTopByUserOrderByIdDesc(User user);
}