package com.exercise.listingservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.listingservice.dto.UserDTO.CreateUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.GetUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.GetUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.UserDto;
import com.exercise.listingservice.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public GetUserResponseDto getUsers(
            @RequestParam(name = "pageNum", defaultValue = "1") @Min(1) Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) Integer pageSize
    ) {
        GetUserRequestDto requestDto = new GetUserRequestDto();
        requestDto.setPageNum(pageNum - 1);
        requestDto.setPageSize(pageSize);

        return userService.getUsers(requestDto);
    }

    @GetMapping("/users/{id}")
    public Optional<UserDto> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public CreateUserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    /**
        buat input user
        curl -X POST http://localhost:8081/users -H "Content-Type: application/json" -d "{\"username\": \"newuser\"}"
     */
    
}
