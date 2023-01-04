package ru.kata.spring.boot_security.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.RoleDao;
import ru.kata.spring.boot_security.demo.Entities.EnumRoles;
import ru.kata.spring.boot_security.demo.Entities.Role;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private RoleDao roleDao;
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public Role addRole(Role role) {
        roleDao.addRole(role);
        return role;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
    @Transactional(readOnly = true)
    public Set<Role> getUserRoles(Long id) {
        return roleDao.getUserRoles(id);
    }
    @Transactional
    @Override
    public Optional<Role> findByName(EnumRoles name) {
        return Optional.ofNullable(roleDao.findByName(name).stream()
                .findFirst()
                .orElse(addRole(new Role(name))));
    }
}
