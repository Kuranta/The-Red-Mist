package com.springproject.dao;

import com.springproject.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role findRole(String name) {
        return (Role) entityManager.createQuery("from Role where name=: name")
                .setParameter("name",name)
                .getSingleResult();
    }
}