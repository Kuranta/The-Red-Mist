package com.springproject.service;

import com.springproject.dto.UserCreationRequest;
import com.springproject.dto.UserDTO;
import com.springproject.dto.UserUpdateDTO;
import com.springproject.models.User;

import java.util.List;


public interface UserService {
    public List<UserDTO> getUsers();
    public User saveUser(UserCreationRequest userCreationRequest);
    public User updateUser(UserUpdateDTO updateDTO);
    public void deleteUser(Long id);
    public UserDTO getUserById(Long id);
    public UserDTO getUserByEmail(String email);

}
