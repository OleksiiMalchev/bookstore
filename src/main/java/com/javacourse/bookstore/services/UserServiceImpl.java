package com.javacourse.bookstore.services;

import com.javacourse.bookstore.domain.dto.UserReqDTO;
import com.javacourse.bookstore.domain.dto.UserRespDTO;
import com.javacourse.bookstore.mappers.MapperUser;
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
    public Optional<UserRespDTO> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(mapperUser::toUserRespDTO);
    }

    @Override
    public Optional<UserRespDTO> createUser(UserReqDTO userReqDTO) {
        return Optional.ofNullable(mapperUser.userReqDTOToUser(userReqDTO))
                .map(userRepository::save)
                .map(mapperUser::toUserRespDTO);
    }

    @Override
    public Optional<UserRespDTO> updateUser(Long userId, UserReqDTO userReqDTO) {
        return userRepository.findById(userId)
                .map(u -> {
                    if (userReqDTO != null) {
                        u.setFirstName(userReqDTO.getFirstName());
                        u.setLastName(userReqDTO.getLastName());
                        u.setEmail(userReqDTO.getEmail());
                        u.setNickName(u.getNickName());
                        u.setAge(u.getAge());
                        u.setPhoneNumber(u.getPhoneNumber());
                        u.setDateOfBirth(u.getDateOfBirth());
                        return u;
                    }
                    return null;
                }).map(mapperUser::toUserRespDTO);
    }

    @Override
    public Optional<UserRespDTO> deleteUser(Long userId) {
        Optional<UserRespDTO> userRespDTO = userRepository.findById(userId).map(mapperUser::toUserRespDTO);
        if (userRespDTO.isPresent()) {
            userRepository.deleteById(userId);
        }
        return userRespDTO;
    }
}
