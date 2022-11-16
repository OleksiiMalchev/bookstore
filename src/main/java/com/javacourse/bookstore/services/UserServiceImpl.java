package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.User;
import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.mappers.MapperUser;
import com.javacourse.bookstore.repositories.UserRepositories;
import com.javacourse.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositories userRepositories;
    private final MapperUser mapperUser;
    private final UserRepository userRepository;

    @Override
    public List<UserRespDTO> getAllUser() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(mapperUser::toUserRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserRespDTO getUserByID(Long userId) {
        return userRepository.findById(userId)
                .map(mapperUser::toUserRespDTO)
                .orElse(null);
    }

    @Override
    public UserRespDTO createUser(UserReqDTO userReqDTO) {
        return Optional.ofNullable(mapperUser.userReqDTOToUser(userReqDTO))
                .map(userRepository::save)
                .map(mapperUser::toUserRespDTO)
                .orElse(null);
    }

    @Override
    public UserRespDTO updateUser(Long userId, UserReqDTO userReqDTO) {
        User userInBase = userRepository.findById(userId).get();
        User userFromReq = mapperUser.userReqDTOToUser(userReqDTO);
        userInBase.setFirstName(userFromReq.getFirstName());
        userInBase.setLastName(userFromReq.getLastName());
        userInBase.setEmail(userFromReq.getEmail());
        userInBase.setNickName(userFromReq.getNickName());
        userRepository.save(userInBase);
        return mapperUser.toUserRespDTO(userInBase);
    }

    @Override
    public UserRespDTO deleteUser(Long userId) {
        User userInBase = userRepository.findById(userId).get();
        UserRespDTO userRespDTO = mapperUser.toUserRespDTO(userInBase);
        userRepository.delete(userInBase);
        return userRespDTO;
    }
}
