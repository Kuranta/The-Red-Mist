package com.springproject.controllers;

import com.springproject.models.User;
import com.springproject.service.RoleService;
import com.springproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @SneakyThrows
    public void saveUser(@ModelAttribute User user, HttpServletResponse response) {
        userService.saveUser(user);
        response.sendRedirect("http://localhost:8080/admin");
    }

    @GetMapping("/editUser/{id}")
    public User editUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @PostMapping("/deleteUser/{id}")
    @SneakyThrows
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
