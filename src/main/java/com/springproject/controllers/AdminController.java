package com.springproject.controllers;

import com.springproject.models.User;
import com.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminWelcome(Model model) {
        model.addAttribute("userList", userService.readAllUser());
        return "admin";
    }

    @GetMapping("/new")
    public String creatingUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";

    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
