package com.exercise.listingservice.service;

import com.exercise.listingservice.dto.ListingDTO.CreateListingRequestDto;
import com.exercise.listingservice.dto.ListingDTO.CreateListingResponseDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsResponseDto;

public interface ListingService {

    GetListingsResponseDto getListings(GetListingsRequestDto requestDto);

    CreateListingResponseDto createListing(CreateListingRequestDto requestDto);
}
