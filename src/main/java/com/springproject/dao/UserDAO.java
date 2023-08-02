package com.springproject.dao;

import java.util.List;
import java.util.Set;

import com.springproject.models.Role;
import com.springproject.models.User;

public interface UserDAO {

    public List<User> readAllUser();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public List<Role> getRoles();

    }
