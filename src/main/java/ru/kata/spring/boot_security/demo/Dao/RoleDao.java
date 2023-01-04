package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.Entities.EnumRoles;
import ru.kata.spring.boot_security.demo.Entities.Role;
import ru.kata.spring.boot_security.demo.Entities.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface RoleDao {
    Role addRole(Role role);
    Set<Role> getAllRoles();
    Set<Role> getUserRoles(Long id);

    Optional<Role> findByName(EnumRoles name);
}
