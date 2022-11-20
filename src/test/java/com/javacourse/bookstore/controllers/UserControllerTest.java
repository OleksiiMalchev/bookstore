package com.javacourse.bookstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacourse.bookstore.BookstoreApplication;
import com.javacourse.bookstore.domain.User;
import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BookstoreApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userServiceImpl;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUserById() throws Exception {
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").id(125L).build();
        Mockito.when(userServiceImpl.getUserById(125L))
                .thenReturn(Optional.ofNullable(UserRespDTO
                        .builder()
                        .email("a@gmail.com")
                        .firstName("Alex")
                        .build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",125L))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value(user.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email")
                        .value(user.getEmail()));
    }

    @Test
    void allUsers() throws Exception {
        UserRespDTO firstUserRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        UserRespDTO secondUserRespDTO = UserRespDTO.builder()
                .email("a1@gmail.com").firstName("Alex1").build();
        UserRespDTO thirdUserRespDTO = UserRespDTO.builder()
                .email("a2@gmail.com").firstName("Alex2").build();
        Mockito.when(userServiceImpl.getAllUser())
                .thenReturn(List.of(firstUserRespDTO, secondUserRespDTO, thirdUserRespDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName")
                        .value(firstUserRespDTO.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].firstName")
                        .value(thirdUserRespDTO.getFirstName()));
    }

    @Test
    void createUser() throws Exception {
        UserReqDTO userReqDTO = UserReqDTO.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();

        Mockito
                .when(userServiceImpl.createUser(Mockito.any(UserReqDTO.class))).thenReturn(Optional.ofNullable(userRespDTO));
        String writeValueAsString = objectMapper.writeValueAsString(userReqDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/user").content(writeValueAsString)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userRespDTO.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userRespDTO.getEmail()));
    }

    @Test
    void updateUser() throws Exception {
        UserReqDTO userReqDTO = UserReqDTO.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").id(555L).build();
        Mockito
                .when(userServiceImpl.updateUser(Mockito.any(),Mockito.any(UserReqDTO.class)))
                .thenReturn(Optional.ofNullable(userRespDTO));
        String writeValueAsString = objectMapper.writeValueAsString(userReqDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}",555L).content(writeValueAsString)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userRespDTO.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userRespDTO.getEmail()));


    }

    @Test
    void deleteUser() throws Exception {
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).id(444L).email("a@gmail.com")
                .firstName("Alex").lastName("DSJ").id(555L).build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        Mockito.when(userServiceImpl.deleteUser(user.getId())).thenReturn(Optional.ofNullable(userRespDTO));
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userRespDTO.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userRespDTO.getEmail()));
    }
}