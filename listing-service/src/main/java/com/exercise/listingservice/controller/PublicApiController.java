package com.exercise.listingservice.controller;

import com.exercise.listingservice.dto.ListingDTO.CreateListingRequestDto;
import com.exercise.listingservice.dto.ListingDTO.CreateListingResponseDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
import com.exercise.listingservice.dto.PublicApiDTO.PublicApiGetListingsRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserResponseDto;
import com.exercise.listingservice.service.PublicApiService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/public-api")
public class PublicApiController {

    @Autowired
    private PublicApiService publicApiService;

    @GetMapping("/listings")
    public Object getListings(
        @RequestParam(name = "pageNum", defaultValue = "1") @Min(1) int pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) int pageSize,
        @RequestParam(name = "userId", required = false) Integer userId
) {
    int zeroBasedPageNum = pageNum - 1;

    if (userId != null) {
        PublicApiGetListingsRequestDto publicApiRequestDto = new PublicApiGetListingsRequestDto();
        publicApiRequestDto.setPageNum(zeroBasedPageNum);
        publicApiRequestDto.setPageSize(pageSize);
        publicApiRequestDto.setUserId(userId);

        return publicApiService.getPublicApiListings(publicApiRequestDto);
    } else {
        GetListingsRequestDto generalRequestDto = new GetListingsRequestDto();
        generalRequestDto.setPageNum(zeroBasedPageNum);
        generalRequestDto.setPageSize(pageSize);

        return publicApiService.getListings(generalRequestDto);
    }
}


    @PostMapping("/listings")
    public CreateListingResponseDto createListing(@Valid CreateListingRequestDto requestDto) {
        return publicApiService.createListing(requestDto);
    }

    @PostMapping("/users")
    public CreateUserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {
        return publicApiService.createUser(requestDto);
    }
}
