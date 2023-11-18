package com.springproject.dto;

import com.springproject.models.Role;
import com.springproject.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper implements Function<User,UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoles()
                .stream()
                .map(role -> new Role(role.getId(), role.getName()))
                .collect(Collectors.toSet()));
    }
}
