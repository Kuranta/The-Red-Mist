package com.springproject.controllers;

import com.springproject.models.User;
import com.springproject.service.RoleService;
import com.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public ViewController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String adminView(Model model){
        model.addAttribute("userList", userService.getUsers());
        model.addAttribute("roleList",roleService.getRoles());
        model.addAttribute("user", new User());
        return "admin";
    }

    @GetMapping("/user")
    public String userView(Model model){
        model.addAttribute("userList", userService.getUsers());
        model.addAttribute("roleList",roleService.getRoles());
        model.addAttribute("user", new User());
        return "user";
    }



}
