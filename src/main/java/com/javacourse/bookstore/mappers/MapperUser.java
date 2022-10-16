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
                .map(u -> new User(u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getNickName()))
                .orElse(null);
    }
    public UserRespDTO toUserRespDTO(User user) {
        return Optional.ofNullable(user)
                .stream()
                .findAny()
                .map(u -> new UserRespDTO(u.getFirstName(),
                        u.getEmail(),
                        u.getNickName(),
                        u.getIDUser()))
                .orElse(null);
    }
}
