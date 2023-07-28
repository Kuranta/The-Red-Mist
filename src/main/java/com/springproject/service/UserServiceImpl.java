package com.springproject.service;


import com.springproject.dao.UserDAO;
import com.springproject.models.Role;
import com.springproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public List<User> readAllUser() {
        return userDAO.readAllUser();
    }


    //TODO rawPassword cannot be null, IllegalArgumentException.
    @Override
    @Transactional
    public void saveUser(User user) {
        user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
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
