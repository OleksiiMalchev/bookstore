package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.mappers.domain.User;
import com.javacourse.bookstore.mappers.domain.dto.UserReqDTO;
import com.javacourse.bookstore.mappers.domain.dto.UserRespDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class MapperUserTest {
    @Autowired
    private MapperUser mapperUser;
    private final UserReqDTO userReqDTO = UserReqDTO.builder()
            .firstName("Alex").lastName("Ramwtainov").email("ar@gmail.com").nickName("Al")
            .age(25).build();
    private final User user = User.builder().firstName("Alex").lastName("Ramwtainov").age(25)
            .dateOfBirth(LocalDate.of(1881,05,20)).build();
    @Test
    void userReqDTOToUser() {
        User userTest = mapperUser.userReqDTOToUser(userReqDTO);
        Assertions.assertEquals(userTest.getFirstName(), userReqDTO.getFirstName());
        Assertions.assertEquals(userTest.getLastName(), userReqDTO.getLastName());
        Assertions.assertEquals(userTest.getDateOfBirth(), userReqDTO.getDateOfBirth());
        Assertions.assertNotNull(userTest);
    }
    @Test
    void toUserRespDTO() {
        UserRespDTO userRespDTO = mapperUser.toUserRespDTO(user);
        Assertions.assertNotNull(userRespDTO);
        Assertions.assertEquals(user.getFirstName(), userRespDTO.getFirstName());
        Assertions.assertEquals(user.getEmail(), userRespDTO.getEmail());
        Assertions.assertEquals(user.getNickName(), userRespDTO.getNickName());
    }
}