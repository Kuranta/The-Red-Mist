package com.springproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.dto.UserCreationRequest;
import com.springproject.dto.UserDTO;
import com.springproject.dto.UserUpdateDTO;
import com.springproject.models.Role;
import com.springproject.models.User;
import com.springproject.repository.RoleRepository;
import com.springproject.repository.UserRepository;
import com.springproject.service.RoleServiceImpl;
import com.springproject.service.UserServiceImpl;
import com.springproject.utils.JwtTokenUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@WebMvcTest(RestCrudController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private RoleServiceImpl roleService;

    @MockBean
    private JwtTokenUtils jwtTokenUtils;

    @Test
    @WithMockUser(username = "seleznev95@bk.ru")
    void getUsers_shouldCallUserService_thenStatus200andReturnUserDTOList() throws Exception {
        //given
        UserDTO userDTO = new UserDTO(
                1L,"Fedor","Seleznev","seleznev95@bk.ru",19, Collections.singleton(new Role(2l,"USER")));
        List<UserDTO> userList = List.of(userDTO);
        //when
        Mockito.when(userService.getUsers()).thenReturn(userList);
        //then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", CoreMatchers.is(userDTO.firstName())));
    }

    @Test
    @WithMockUser(username = "seleznev95@bk.ru")
    void getSingleUser_shouldCallUserService_thenStatus200() throws Exception{
        //given
        Long userId = 1l;
        UserDTO userDTO = new UserDTO(
                1L,"Fedor","Seleznev","seleznev95@bk.ru",19, Collections.singleton(new Role(2l,"USER")));
        //when
        Mockito.when(userService.getUserById(userId)).thenReturn(userDTO);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(userDTO.firstName())));
    }

    @Test
    @WithMockUser(username = "seleznev95@bk.ru")
    void saveUser_shouldCallUserService_thenStatus200AndReturnUserCreationRequest() throws Exception{
        //given
        UserCreationRequest userCreationRequest =new UserCreationRequest(
                "Fedor","Seleznev",19,"password","seleznev@bk.ru", Collections.singleton(new Role(2l,"USER")));
        User userCreationRequestToUser = new User(
                1L,"Fedor","Seleznev",19,"seleznev95@bk.ru","password", Collections.singleton(new Role(2l,"USER")));
        //when
        Mockito.when(userService.saveUser(userCreationRequest)).thenReturn(userCreationRequestToUser);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreationRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userCreationRequest.email())));
    }

    @Test
    @WithMockUser(username = "seleznev95@bk.ru")
    void editUser_shouldCallUserService_thenReturnStatus200AndReturnEditedUser() throws Exception {
        //given
        UserUpdateDTO updateDTO = new UserUpdateDTO(
                1L,"Fedor","Seleznev",1984,"seleznev95@bk.ru","password", Collections.singleton(new Role(2l,"USER")));
        User editedUser = new User(
                2L,"Fedor","Selseznev",129,"zymerok95@bk.ru","passsword", Collections.singleton(new Role(3l,"Zymerok")));
        //when
        Mockito.when(userService.updateUser(updateDTO)).thenReturn(editedUser);
        //then
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(editedUser.getEmail())));
    }

    @Test
    @WithMockUser(username = "seleznev95@bk.ru")
    void deleteUser_shouldCallUserService_thenReturnStatus200AndReturnVoid() throws Exception {
        //given
        Long id = 1l;
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        Mockito.verify(userService).deleteUser(id);

    }
}