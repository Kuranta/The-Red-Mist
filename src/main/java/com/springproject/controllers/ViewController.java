package com.springproject.controllers;

import com.springproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
    private final RoleService roleService;

    @Autowired
    public ViewController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String adminView(Model model){
        model.addAttribute("roleList",roleService.getRoles());
        return "admin";
    }

    @GetMapping("/user")
    public String userView(){
        return "user";
    }

    @GetMapping("/login")
    public String loginView(){
        return "login";
    }
}
