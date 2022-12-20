package ru.kata.spring.boot_security.demo.Dao;

import ru.kata.spring.boot_security.demo.Entities.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void delete(Long id);

    void update(Long id, User user);
}
