package com.springproject.dto;

import com.springproject.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@NotEmpty
public record UserCreationRequest (String firstName, String lastName, Integer age, String email, String password, Set<Role> roles){
}
