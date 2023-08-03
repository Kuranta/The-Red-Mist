package com.springproject.dao;

import com.springproject.models.Role;
import com.springproject.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> readAllUser() {
        return entityManager.createQuery("from User").getResultList();
    }
    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }
    @Override
    public User getUserById(Long id){
        return entityManager.find(User.class,id);
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("from User where email=: email")
                .setParameter("email",email)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }


}
