package com.springproject.controllers;

import com.springproject.models.User;
import com.springproject.service.RoleService;
import com.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/getUsers")
    public List<User> admin() {
        return userService.getUsers();
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "{\"status\":\"success\"}";
    }

    @GetMapping("/edit/{id}")
    public User editUser(@RequestParam Long id) {
        return userService.getUserById(id);
    }
//
//
//    @PostMapping("/deleteUser/{id}")
//    public void deleteUser(@PathVariable("id") Long id){
//        userService.deleteUser(id);
//    }
}
