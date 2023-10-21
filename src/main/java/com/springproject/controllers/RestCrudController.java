package com.springproject.controllers;

import com.springproject.models.User;
import com.springproject.service.RoleService;
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
    public List<User> admin() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getSingleUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @SneakyThrows
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PatchMapping("/users/{id}")
    public void editUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    @SneakyThrows
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
