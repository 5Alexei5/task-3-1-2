package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByAuthority(String name);
}
