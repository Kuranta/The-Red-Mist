package com.springproject.controllers;

import com.springproject.dto.UserCreationRequest;
import com.springproject.dto.UserDTO;
import com.springproject.dto.UserUpdateDTO;
import com.springproject.models.User;
import com.springproject.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestCrudController {

    private final UserService userService;

    @Autowired
    public RestCrudController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getSingleUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @SneakyThrows
    public UserCreationRequest saveUser(@RequestBody UserCreationRequest userCreationRequest) {
        userService.saveUser(userCreationRequest);
        return userCreationRequest;
    }

    @PatchMapping("/users/{id}")
    public User editUser(@RequestBody UserUpdateDTO updateDTO) {
        return userService.updateUser(updateDTO);
    }

    @DeleteMapping("/users/{id}")
    @SneakyThrows
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
