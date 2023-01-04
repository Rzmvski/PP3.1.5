package ru.kata.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.Dao.RoleDao;
import ru.kata.spring.boot_security.demo.Dao.UserDao;
import ru.kata.spring.boot_security.demo.Entities.EnumRoles;
import ru.kata.spring.boot_security.demo.Entities.User;
import ru.kata.spring.boot_security.demo.Entities.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(Long id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getRoles().forEach(r -> {
//                            switch (r.getName()) {
//                                case ROLE_USER:
//                                    user.setRoles(roleService.findByName(EnumRoles.ROLE_USER).stream().collect(Collectors.toSet()));
//                                    break;
//                                case ROLE_ADMIN:
//                                    user.setRoles(roleService.findByName(EnumRoles.ROLE_ADMIN).stream().collect(Collectors.toSet()));
//                                    break;
//                            }
//                        });
        userDao.update(id, user);
    }
}
