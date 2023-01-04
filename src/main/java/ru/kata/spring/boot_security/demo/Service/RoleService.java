package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.Entities.EnumRoles;
import ru.kata.spring.boot_security.demo.Entities.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {

    Role addRole(Role role);
    Set<Role> getAllRoles();
    Set<Role> getUserRoles(Long id);
    Optional<Role> findByName(EnumRoles name);
}
