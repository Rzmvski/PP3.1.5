package ru.kata.spring.boot_security.demo.Service;



import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    void delete(Long id);
    void update(Long id, User user);
}