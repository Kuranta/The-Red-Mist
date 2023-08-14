package com.springproject.service;


import com.springproject.dao.UserDAO;
import com.springproject.models.Role;
import com.springproject.models.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.readAllUser();
    }


    @Override
    @Transactional
    @SneakyThrows
    public void saveUser(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role searchRole = roleService.findRole(role.getName());
            roles.add(searchRole);
        }
        user.setRoles(roles);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

}
