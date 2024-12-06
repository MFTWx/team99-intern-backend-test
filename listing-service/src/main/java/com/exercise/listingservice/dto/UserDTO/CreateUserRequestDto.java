package com.exercise.listingservice.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;

public class CreateUserRequestDto {

    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}