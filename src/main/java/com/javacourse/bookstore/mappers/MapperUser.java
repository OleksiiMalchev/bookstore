package com.javacourse.bookstore.mappers;

import com.javacourse.bookstore.domain.User;
import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperUser {

    public User userReqDTOToUser(UserReqDTO userReqDTO) {
        return Optional.ofNullable(userReqDTO)
                .map(u -> User.builder()
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .email(u.getEmail())
                        .nickName(u.getNickName())
                        .build())
                .orElse(null);
    }
    public UserRespDTO toUserRespDTO(User user) {
        return Optional.ofNullable(user)
                .map(u -> UserRespDTO
                        .builder()
                        .firstName(u.getFirstName())
                        .email(u.getEmail())
                        .nickName(u.getNickName())
                        .id(u.getId())
                        .build())
                .orElse(null);
    }
}
