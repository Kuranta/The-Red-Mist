package com.springproject.dto;

import com.springproject.models.Role;

import java.util.Set;

public record UserDTO(Long id, String firstName, String lastName, String email, Integer age, Set<Role> roles) {
}
