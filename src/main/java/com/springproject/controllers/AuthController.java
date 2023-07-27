package com.springproject.controllers;

import com.springproject.models.User;
import com.springproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("userForm",new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String submitRegisterPage(@ModelAttribute("userForm") User userForm,
                                     @Valid BindingResult bindingResult){
        if (bindingResult.hasErrors())
        userService.saveUser(userForm);
        return "redirect:/";
    }
}
