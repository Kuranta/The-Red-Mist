package com.springproject.controllers;

import com.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/user")
//    public String printWelcome(Model model) {
//        model.addAttribute("userList", userService.getUsers());
//        return "user";
//    }



}

