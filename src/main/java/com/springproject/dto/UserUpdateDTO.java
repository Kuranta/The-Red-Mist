package com.springproject.dto;

import com.springproject.models.Role;

import java.util.Set;

public record UserUpdateDTO(Long id, String firstName, String lastName, Integer age, String email, String password, Set<Role> roles) {

}
