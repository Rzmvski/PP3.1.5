package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Entities.EnumRoles;
import ru.kata.spring.boot_security.demo.Entities.Role;
import ru.kata.spring.boot_security.demo.Entities.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role addRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r from Role r", Role.class).getResultList());
    }

    @Override
    public Set<Role> getUserRoles(Long id) {
        return new HashSet<>(entityManager.createQuery("SELECT Role FROM Role, User where name = :id")
                .setParameter("id", id).getResultList());
        }

    @Override
    public Optional<Role> findByName(EnumRoles name) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name);
        return query.getResultList().stream().findFirst();

    }
}
