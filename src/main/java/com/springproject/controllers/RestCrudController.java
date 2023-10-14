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
    private final RoleService roleService;

    @Autowired
    public RestCrudController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getUsers")
    public List<User> admin() {
        return userService.getUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getSingleUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/saveUser")
    @SneakyThrows
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PatchMapping("/editUser/{id}")
    public void editUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    @SneakyThrows
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
