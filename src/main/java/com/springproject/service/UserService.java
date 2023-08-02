package com.springproject.service;

import com.springproject.models.Role;
import com.springproject.models.User;

import java.util.List;
import java.util.Set;


public interface UserService {

    public List<User> readAllUser();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public List<Role> getRoles();


}
