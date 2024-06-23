package com.springproject.service;

import com.springproject.dto.UserCreationRequest;
import com.springproject.dto.UserDTO;
import com.springproject.dto.UserDTOMapper;
import com.springproject.dto.UserUpdateDTO;
import com.springproject.models.Role;
import com.springproject.models.User;
import com.springproject.repository.RoleRepository;
import com.springproject.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserDTOMapper userDTOMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @SneakyThrows
    public User saveUser(UserCreationRequest userCreationRequest) {
        Set<Role> roles = new HashSet<>();
        for (Role role : userCreationRequest.roles()) {
            Role searchRole = roleRepository.findByName(role.getName()).orElseThrow(() -> new NoSuchElementException("Role not found with name: " + role.getName()));
            roles.add(searchRole);
        }
        if (roles.size()<1){
            throw new IllegalAccessException("User must have at least one role");
        }
        User user = new User()
                .setFirstName(userCreationRequest.firstName())
                .setLastName(userCreationRequest.lastName())
                .setAge(userCreationRequest.age())
                .setEmail(userCreationRequest.email())
                .setPassword(passwordEncoder.encode(userCreationRequest.password()))
                .setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    @SneakyThrows
    public User updateUser(UserUpdateDTO updateDTO) {
        Set<Role> roles = new HashSet<>();
        for (Role role : updateDTO.roles()) {
            Role searchRole = roleRepository.findByName(role.getName()).orElseThrow(() -> new NoSuchElementException("Role not found with name: " + role.getName()));
            roles.add(searchRole);
        }

        if (roles.size()<1){
            throw new IllegalAccessException("User must have at least one role");
        }
        User user = new User()
                .setId(updateDTO.id())
                .setFirstName(updateDTO.firstName())
                .setLastName(updateDTO.lastName())
                .setAge(updateDTO.age())
                .setEmail(updateDTO.email())
                .setPassword(passwordEncoder.encode(updateDTO.password()))
                .setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(userDTOMapper).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userDTOMapper).orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }


}
