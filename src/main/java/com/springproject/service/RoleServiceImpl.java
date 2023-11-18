package com.springproject.service;

import com.springproject.models.Role;
import com.springproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@EnableTransactionManagement
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Role not found with name: " + name));
    }
}
