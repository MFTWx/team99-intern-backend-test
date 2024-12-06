package com.exercise.listingservice.service;

import com.exercise.listingservice.dto.ListingDTO.CreateListingRequestDto;
import com.exercise.listingservice.dto.ListingDTO.CreateListingResponseDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsResponseDto;
import com.exercise.listingservice.dto.PublicApiDTO.PublicApiGetListingsRequestDto;
import com.exercise.listingservice.dto.PublicApiDTO.PublicApiGetListingsResponseDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserResponseDto;

public interface PublicApiService {

    GetListingsResponseDto getListings(GetListingsRequestDto requestDto);

    PublicApiGetListingsResponseDto getPublicApiListings(PublicApiGetListingsRequestDto requestDto);

    CreateListingResponseDto createListing(CreateListingRequestDto requestDto);

    CreateUserResponseDto createUser(CreateUserRequestDto requestDto);
}