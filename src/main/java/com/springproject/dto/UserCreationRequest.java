package com.springproject.dto;

import com.springproject.models.Role;

import java.util.Set;

public record UserCreationRequest (String firstName, String lastName, Integer age, String email, String password, Set<Role> roles){
}
