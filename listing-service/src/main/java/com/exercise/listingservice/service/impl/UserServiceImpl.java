package com.exercise.listingservice.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exercise.listingservice.dto.UserDTO.CreateUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.GetUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.GetUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.UserDto;
import com.exercise.listingservice.entity.User;
import com.exercise.listingservice.repository.UserRepository;
import com.exercise.listingservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public GetUserResponseDto getUsers(GetUserRequestDto requestDto) {
        PageRequest pageRequest = PageRequest.of(requestDto.getPageNum(),
                requestDto.getPageSize(), Sort.by("createdAt").descending()
        );

        List<User> result;
        if (requestDto.getId() != null) {
            result = userRepository.findById(requestDto.getId())
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } else {
            result = userRepository.findAll(pageRequest).getContent();
        }

        List<UserDto> userDtoList = result.stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());

        GetUserResponseDto responseDto = new GetUserResponseDto();
        responseDto.setResult(true);
        responseDto.setUsers(userDtoList);

        return responseDto;
    }

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());

        final Long timestampInMicroSecond = nowInEpochMicroSecond();
        user.setCreatedAt(timestampInMicroSecond);
        user.setUpdatedAt(timestampInMicroSecond);

        userRepository.save(user);

        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setResult(true);
        responseDto.setUser(convertUserToUserDto(user));

        return responseDto;
    }

    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    private Long nowInEpochMicroSecond() {
        return ChronoUnit.MICROS.between(Instant.EPOCH, Instant.now());
    }

    @Override
    public Optional<UserDto> getUserById(Integer id) {
        return userRepository.findById(id)
                .map(this::convertUserToUserDto);
    }
    
}
