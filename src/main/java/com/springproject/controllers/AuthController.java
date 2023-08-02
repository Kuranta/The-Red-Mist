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
    public String getRegistrationForm(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/registration/save")
    public String submitRegisterPage(@ModelAttribute("userForm") User user,
                                     @Valid BindingResult bindingResult, Model model){
        User existingUserEmail = userService.getUserByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", "There is already a user with this email.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUserWithDefaultRole(user);
        return "redirect:/";
    }
}
