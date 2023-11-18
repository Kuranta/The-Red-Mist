package com.springproject.utils;

import com.springproject.models.Role;
import com.springproject.models.User;
import com.springproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilsTest {
    @Mock
    private JwtTokenUtils jwtTokenUtils;


    @Test
    void generateToken_shouldReturnJwtToken_whenUserExists() {
        //given
        User user =new User(1L,"Fedor","Seleznev",19,"seleznev95@bk.ru","password", Collections.singleton(new Role(2l,"USER")));
        String expectedToken = "token";
        //when
        Mockito.when(jwtTokenUtils.generateToken(user)).thenReturn(expectedToken);
        String actualJwtToken = jwtTokenUtils.generateToken(user);
        //then
        assertNotNull(actualJwtToken);
        assertEquals(expectedToken,actualJwtToken);

    }

    @Test
    void getEmail_shouldReturnUserEmail_whenJwtTokenExists() {
        //given
        String expectedToken = "token";
        String expectedEmail = "seleznev95@bk.ru";
        //when
        Mockito.when(jwtTokenUtils.getEmail(expectedToken)).thenReturn(expectedEmail);
        String actualEmail = jwtTokenUtils.getEmail(expectedToken);
        //then
        assertNotNull(actualEmail);
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void getRoles_shouldReturnUserRoles_whenJwtTokenExists() {
        //given
        String expectedToken = "token";
        List<String> expectedRoleList = List.of("USER");
        //when
        Mockito.when(jwtTokenUtils.getRoles(expectedToken)).thenReturn(expectedRoleList);
        List<String> actualRoleList = jwtTokenUtils.getRoles(expectedToken);
        //then
        assertNotNull(actualRoleList);
        assertEquals(expectedRoleList,actualRoleList);
    }

}