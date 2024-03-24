package com.springproject.service;

import com.springproject.dto.UserCreationRequest;
import com.springproject.dto.UserDTO;
import com.springproject.dto.UserDTOMapper;
import com.springproject.models.Role;
import com.springproject.models.User;
import com.springproject.repository.RoleRepository;
import com.springproject.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserDTOMapper userDTOMapper;

    @Test
    void getUsers_shouldCallUserRepository_returnListUserDTO(){
        //given
        User user1 = new User(
                1L,"Fartish","Cleared",28,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));
        User user2 = new User(
                2L,"Roland","BlackSilence",21,"sorrow@lor.korea","password", Collections.singleton(new Role(2l,"USER")));
        List<User> userList = List.of(user1,user2);
        //when
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> userDTOS = userService.getUsers();
        //then
        Assertions.assertEquals(2,userDTOS.size());


    }

    @Test
    void getSingleUser_shouldCallUserRepository_returnUserDTO(){
        //given
        Long id = 1L;
        User user = new User(
                1L,"Fartish","Cleared",28,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));
        UserDTO expectedDTO = new UserDTO(
                1L,"Fartish","Cleared","fartish@ch.ru",28, Collections.singleton(new Role(2l,"USER")));
        //when
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        Mockito.when(userDTOMapper.apply(user)).thenReturn(expectedDTO);
        UserDTO actualDTO = userService.getUserById(id);
        //then
        Assertions.assertNotNull(actualDTO);
        Assertions.assertEquals(expectedDTO,actualDTO);
    }

    @Test
    void getSingleUser_shouldThrowNoSuchElementException_userWithSuchIDNotExist(){
        //given
        Long id = 8L;
        //when
        Mockito.when(userRepository.findById(id)).thenThrow(new NoSuchElementException("User with such ID is not exist: " + id));
        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> userRepository.findById(id));
    }

    @Test
    void saveUser_shouldCallUserRepository_returnAndSaveUserDTO(){
        //given
        UserCreationRequest userCreationRequest = new UserCreationRequest(
                "Fartish","Cleared",28,"fartish@ch.ru", "password" ,Collections.singleton(new Role(2l,"USER")));
        User user =new User(
                1L,"Fartish","Cleared",28,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));
        Role role = new Role(2l,"USER");
        //when
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(roleRepository.findByName("USER")).thenReturn(Optional.ofNullable(role));
        //then
        User actualUser = userService.saveUser(userCreationRequest);
        Assertions.assertNotNull(actualUser);
    }

    @Test
    void saveUser_shouldThrowConstrainViolationException_someFieldsIncorrect(){
        //given
        User user =new User(
                1L,"","",19,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));
        //when
        Mockito.when(userRepository.save(Mockito.any())).thenThrow(ConstraintViolationException.class);
        //then
        Assertions.assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }

    @Test
    void deleteUser_shouldCallUserRepository_returnVoid(){
        //given
        Long id = 1L;
        User user = new User(
                1L,"Fartish","Cleared",28,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));
        //when
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        userService.deleteUser(id);
        //then
        Mockito.verify(userRepository).deleteById(id);
    }

    @Test
    void deleteUser_shouldThrowNoSuchElementException_userWithSuchIDNotExist(){
        //given
        Long id = 1984L;
        User user = new User(
                1L,"Fartish","Cleared",28,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));
        //when
        Mockito.doThrow(new NoSuchElementException()).when(userRepository).deleteById(id);
        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> userRepository.deleteById(id));
    }
}
