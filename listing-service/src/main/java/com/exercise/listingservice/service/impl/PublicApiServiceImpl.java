package com.exercise.listingservice.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exercise.listingservice.dto.ListingDTO.CreateListingRequestDto;
import com.exercise.listingservice.dto.ListingDTO.CreateListingResponseDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsRequestDto;
import com.exercise.listingservice.dto.ListingDTO.GetListingsResponseDto;
import com.exercise.listingservice.dto.ListingDTO.ListingDto;
import com.exercise.listingservice.dto.PublicApiDTO.PublicApiGetListingsRequestDto;
import com.exercise.listingservice.dto.PublicApiDTO.PublicApiGetListingsResponseDto;
import com.exercise.listingservice.dto.PublicApiDTO.PublicApiListingDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserRequestDto;
import com.exercise.listingservice.dto.UserDTO.CreateUserResponseDto;
import com.exercise.listingservice.dto.UserDTO.UserDto;
import com.exercise.listingservice.entity.Listing;
import com.exercise.listingservice.entity.User;
import com.exercise.listingservice.repository.ListingRepository;
import com.exercise.listingservice.repository.UserRepository;
import com.exercise.listingservice.service.PublicApiService;

@Service
public class PublicApiServiceImpl implements PublicApiService {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateListingResponseDto createListing(CreateListingRequestDto requestDto) {
        Listing listing = new Listing();
        listing.setPrice(requestDto.getPrice());
        listing.setUserId(requestDto.getUserId());
        listing.setListingType(requestDto.getListingType());

        final Long timestampInMicroSecond = nowInEpochMicroSecond();
        listing.setCreatedAt(timestampInMicroSecond);
        listing.setUpdatedAt(timestampInMicroSecond);

        listingRepository.save(listing);

        CreateListingResponseDto responseDto = new CreateListingResponseDto();
        responseDto.setResult(true);
        responseDto.setListing(convertListingToListingDto(listing));

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

    private ListingDto convertListingToListingDto(Listing listing) {
        ListingDto listingDto = new ListingDto();
        BeanUtils.copyProperties(listing, listingDto);

        return listingDto;
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
    public PublicApiGetListingsResponseDto getPublicApiListings(PublicApiGetListingsRequestDto requestDto) {
        PageRequest pageRequest = PageRequest.of(requestDto.getPageNum(),
                requestDto.getPageSize(), Sort.by("createdAt").descending()
        );

        List<Listing> result = Optional.ofNullable(requestDto.getUserId())
                .map(id -> listingRepository.findByUserId(id, pageRequest))
                .orElseGet(() -> listingRepository.findAll(pageRequest).getContent());

        List<PublicApiListingDto> listingDtoList = result.stream()
                .map(this::convertListingToPublicApiListingDto)
                .collect(Collectors.toList());

        PublicApiGetListingsResponseDto responseDto = new PublicApiGetListingsResponseDto();
        responseDto.setResult(true);
        responseDto.setListings(listingDtoList);

        return responseDto;
    }

    private PublicApiListingDto convertListingToPublicApiListingDto(Listing listing) {
        PublicApiListingDto listingDto = new PublicApiListingDto();

        listingDto.setId(listing.getId());
        listingDto.setListingType(listing.getListingType());
        listingDto.setPrice(listing.getPrice());
        listingDto.setCreatedAt(listing.getCreatedAt());
        listingDto.setUpdatedAt(listing.getUpdatedAt());

        if (listing.getUserId() != null) {
            Optional<User> userOptional = userRepository.findById(listing.getUserId());
            userOptional.ifPresent(user -> {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setCreatedAt(user.getCreatedAt());
                userDto.setUpdatedAt(user.getUpdatedAt());
                listingDto.setUser(userDto);
            });
        }

        return listingDto;
    }

    @Override
    public GetListingsResponseDto getListings(GetListingsRequestDto requestDto) {
        PageRequest pageRequest = PageRequest.of(requestDto.getPageNum(),
                requestDto.getPageSize(), Sort.by("createdAt").descending()
        );

        List<Listing> result = Optional.ofNullable(requestDto.getUserId())
                .map(id -> listingRepository.findByUserId(id, pageRequest))
                .orElseGet(() -> listingRepository.findAll(pageRequest).getContent());

        List<ListingDto> listingDtoList = result.stream()
                .map(this::convertListingToListingDto)
                .collect(Collectors.toList());

        GetListingsResponseDto responseDto = new GetListingsResponseDto();
        responseDto.setResult(true);
        responseDto.setListings(listingDtoList);

        return responseDto;
    }
    
}
