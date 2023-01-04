package ru.kata.spring.boot_security.demo.Dao;


import net.bytebuddy.dynamic.DynamicType;
import ru.kata.spring.boot_security.demo.Entities.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where id = :id", User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }
    @Override
    public Optional<User> getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where username = :username", User.class)
                .setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void update(Long id, User updatedUser) {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());
        user.setRoles(updatedUser.getRoles());
    }
}
