package com.springproject.dao;

import com.springproject.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


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

}
