package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.User;
import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.mappers.MapperUser;
import com.javacourse.bookstore.repositories.UserRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @MockBean
    private UserRepositories userRepositories;
    @MockBean
    private MapperUser mapperUser;

    @Test
    void getAllUser() {
        User firstUser = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").build();
        User secondUser = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(101).email("a1@gmail.com").firstName("Alex1").lastName("DSJ1").build();
        User thirdUser = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12)).age(101)
                .email("a1@gmail.com").firstName("Alex2").lastName("DSJ2").build();
        UserRespDTO firstUserRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        UserRespDTO secondUserRespDTO = UserRespDTO.builder()
                .email("a1@gmail.com").firstName("Alex1").build();
        UserRespDTO thirdUserRespDTO = UserRespDTO.builder()
                .email("a1@gmail.com").firstName("Alex2").build();
        Mockito.when(userRepositories.getAllUser()).thenReturn(List.of(firstUser, secondUser, thirdUser));
        Mockito.when(mapperUser.toUserRespDTO(firstUser)).thenReturn(firstUserRespDTO);
        Mockito.when(mapperUser.toUserRespDTO(secondUser)).thenReturn(secondUserRespDTO);
        Mockito.when(mapperUser.toUserRespDTO(thirdUser)).thenReturn(thirdUserRespDTO);
        List<UserRespDTO> allUser = userService.getAllUser();
        Assertions.assertEquals(allUser.size(), 3);
        Assertions.assertEquals(allUser.get(0).getFirstName(), firstUser.getFirstName());
        Assertions.assertEquals(allUser.get(1).getFirstName(), secondUser.getFirstName());
        Assertions.assertEquals(allUser.get(2).getFirstName(), thirdUser.getFirstName());
    }

    @Test
    void getUserByID() {
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com")
                .firstName("Alex").lastName("DSJ").iid(555L).build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        Mockito.when(userRepositories.getUserByID(user.getIid())).thenReturn(Optional.of(user));
        Mockito.when(mapperUser.toUserRespDTO(user)).thenReturn(userRespDTO);
        UserRespDTO userByID = userService.getUserByID(user.getIid());
        Assertions.assertEquals(userByID.getEmail(), user.getEmail());
        Assertions.assertEquals(userByID.getFirstName(), user.getFirstName());
    }

    @Test
    void createUser() {
        UserReqDTO userReqDTO = UserReqDTO.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").build();
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").iid(555L).build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        Mockito.when(mapperUser.userReqDTOToUser(userReqDTO)).thenReturn(user);
        Mockito.when(userRepositories.saveUserInBase(user)).thenReturn(user);
        Mockito.when(mapperUser.toUserRespDTO(user)).thenReturn(userRespDTO);
        UserRespDTO userCreate = userService.createUser(userReqDTO);
        Assertions.assertEquals(userRespDTO.getFirstName(),userCreate.getFirstName());
        Assertions.assertEquals(userReqDTO.getEmail(), user.getEmail());
    }


    @Test
    void updateUser() {
        UserReqDTO userReqDTO = UserReqDTO.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").build();
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").iid(555L).build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        Mockito.when(mapperUser.userReqDTOToUser(userReqDTO)).thenReturn(user);
        Mockito.when(userRepositories.updateUserByID(user.getIid(),user)).thenReturn(user);
        Mockito.when(mapperUser.toUserRespDTO(user)).thenReturn(userRespDTO);
        UserRespDTO userCreate = userService.updateUser(555L,userReqDTO);
        Assertions.assertEquals(userRespDTO.getFirstName(),userCreate.getFirstName());
        Assertions.assertEquals(userReqDTO.getEmail(), user.getEmail());
    }

    @Test
    void deleteUser() {
        User user = User.builder().dateOfBirth(LocalDate.of(1955, 10, 12))
                .age(10).email("a@gmail.com").firstName("Alex").lastName("DSJ").iid(555L).build();
        UserRespDTO userRespDTO = UserRespDTO.builder().email("a@gmail.com").firstName("Alex").build();
        Mockito.when(userRepositories.deleteUserByID(user.getIid())).thenReturn(Optional.of(user));
        Mockito.when(mapperUser.toUserRespDTO(user)).thenReturn(userRespDTO);
        UserRespDTO deleteUser = userService.deleteUser(555L);
        Assertions.assertEquals(deleteUser.getFirstName(),user.getFirstName());
        Assertions.assertEquals(deleteUser.getEmail(), user.getEmail());
    }
}