package com.exercise.listingservice.service;

import java.util.Optional;

import com.exercise.listingservice.dto.UserDTO.CreateUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.GetUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.GetUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.UserDto;

public interface UserService {
    
    GetUserResponseDto getUsers(GetUserRequestDto requestDto);

    CreateUserResponseDto createUser(CreateUserRequestDto requestDto);

    Optional<UserDto> getUserById(Integer id);

}
