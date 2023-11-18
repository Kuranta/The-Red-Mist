package com.springproject.dto;

import com.springproject.models.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserCreationRequest (String firstName, String lastName, Integer age, String email, String password, Set<Role> roles){
}
