package ru.asayke.jwtappdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asayke.jwtappdemo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}