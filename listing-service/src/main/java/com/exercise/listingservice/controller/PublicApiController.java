package com.exercise.listingservice.controller;

import com.exercise.listingservice.dto.ListingDTO.CreateListingRequestDto;
import com.exercise.listingservice.dto.ListingDTO.CreateListingResponseDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
//import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
//import com.exercise.listingservice.dto.ListingDTO.GetListingsResponseDto;
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
    // public PublicApiGetListingsResponseDto getListings(
    //         @RequestParam(name = "pageNum", defaultValue = "1") @Min(1) int pageNum,
    //         @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) int pageSize,
    //         @RequestParam(name = "userId", required = false) Integer userId
    // ) {

    //     if(userId != null) {
    //         PublicApiGetListingsRequestDto requestDto = new PublicApiGetListingsRequestDto();
    //         requestDto.setPageNum(pageNum - 1); // Page number is zero-based
    //         requestDto.setPageSize(pageSize);
    //         requestDto.setUserId(userId);

    //         return publicApiService.getPublicApiListings(requestDto);
    //     } else {
    //         GetListingsRequestDto requestDto = new GetListingsRequestDto();
    //         requestDto.setPageNum(pageNum - 1);
    //         requestDto.setPageSize(pageSize);
    //         requestDto.setUserId(userId);

    //         return publicApiService.getListings(requestDto);
    //     }
        
    // }
    public Object getListings(
        @RequestParam(name = "pageNum", defaultValue = "1") @Min(1) int pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) int pageSize,
        @RequestParam(name = "userId", required = false) Integer userId
) {
    // Page number is zero-based, adjust it accordingly
    int zeroBasedPageNum = pageNum - 1;

    if (userId != null) {
        // Process when userId is specified
        PublicApiGetListingsRequestDto publicApiRequestDto = new PublicApiGetListingsRequestDto();
        publicApiRequestDto.setPageNum(zeroBasedPageNum);
        publicApiRequestDto.setPageSize(pageSize);
        publicApiRequestDto.setUserId(userId);

        // Fetch listings for the specific user
        return publicApiService.getPublicApiListings(publicApiRequestDto);
    } else {
        // Process when userId is not specified
        GetListingsRequestDto generalRequestDto = new GetListingsRequestDto();
        generalRequestDto.setPageNum(zeroBasedPageNum);
        generalRequestDto.setPageSize(pageSize);

        // Fetch general listings
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
