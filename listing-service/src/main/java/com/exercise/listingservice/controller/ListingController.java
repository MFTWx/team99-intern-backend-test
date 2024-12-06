package com.exercise.listingservice.controller;

import com.exercise.listingservice.dto.ListingDTO.CreateListingRequestDto;
import com.exercise.listingservice.dto.ListingDTO.CreateListingResponseDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsResponseDto;
import com.exercise.listingservice.service.ListingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping("/listings")
    public GetListingsResponseDto getListings(
            @RequestParam(name = "pageNum", defaultValue = "1") @Min(1) Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) Integer pageSize,
            @RequestParam(name = "userId", required = false) @Min(1) Integer userId
    ) {
        GetListingsRequestDto requestDto = new GetListingsRequestDto();
        requestDto.setPageNum(pageNum - 1);
        requestDto.setPageSize(pageSize);
        requestDto.setUserId(userId);

        return listingService.getListings(requestDto);
    }

    @PostMapping("/listings")
    public CreateListingResponseDto createListing(@Valid CreateListingRequestDto requestDto) {
        return listingService.createListing(requestDto);
    }

    /**
        buat input listing
        curl localhost:8081/listings -XPOST \
        -d userId=1 \
        -d listingType=rent \
        -d price=4500

        buat get input specified listing dari user
        curl -X GET "http://localhost:8081/listings?pageNum=1&pageSize=10&userId=1"
     */
}
