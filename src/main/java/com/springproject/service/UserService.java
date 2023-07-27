package com.springproject.service;

import com.springproject.models.User;

import java.util.List;


public interface UserService {

    public List<User> readAllUser();

    public void saveUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);

    public User getUserByEmail(String email);



}
