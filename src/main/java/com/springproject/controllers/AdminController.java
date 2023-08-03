package com.springproject.controllers;

import com.springproject.models.Role;
import com.springproject.models.User;
import com.springproject.service.RoleService;
import com.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping
    public String adminWelcome(Model model) {
        model.addAttribute("userList", userService.getUsers());
        model.addAttribute("roleList",roleService.getRoles());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
//
//    @GetMapping("/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("roleList",userService.getRoles());
//        model.addAttribute("user",user);
//        return "updateUser";
//    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
