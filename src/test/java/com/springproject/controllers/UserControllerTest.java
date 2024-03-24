package com.springproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.dto.UserCreationRequest;
import com.springproject.models.Role;
import com.springproject.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.35"));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    void setRole() throws Exception{
        Role role = new Role(2l,"USER");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(role)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @BeforeAll
    static void beforeAll(){
        mySQLContainer.start();

    }

    @AfterAll
    static void afterAll(){
        mySQLContainer.stop();
    }


    @Test
    @WithMockUser(username = "fartish@ch.ru")
    @Order(1)
    void saveUser_shouldCallUserService_thenStatus200AndReturnUserCreationRequest() throws Exception{
        setRole();
        UserCreationRequest userCreationRequest =new UserCreationRequest(
                "Fartish","Cleared",28,"fartish@ch.ru","password", Collections.singleton(new Role(2l,"USER")));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreationRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(username = "fartish@ch.ru")
    @Order(2)
    void getUsers_shouldCallUserService_thenStatus200andReturnUserDTOList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
    }

    @Test
    @WithMockUser(username = "fartish@ch.ru")
    @Order(3)
    void getSingleUser_shouldCallUserService_thenStatus200() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists());
    }

    @Test
    @WithMockUser(username = "fartish@ch.ru")
    @Order(4)
    void editUser_shouldCallUserService_thenReturnStatus200AndReturnEditedUser() throws Exception {
        User editedUser = new User(
                1L,"Fartish","Cleared",129,"fartishnotcleared@ch.ru","passsword", Collections.singleton(new Role(2l,"USER")));
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(editedUser)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists());
    }

    @Test
    @WithMockUser(username = "fartish@ch.ru")
    @Order(5)
    void deleteUser_shouldCallUserService_thenReturnStatus200AndReturnVoid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").doesNotExist());

    }
}