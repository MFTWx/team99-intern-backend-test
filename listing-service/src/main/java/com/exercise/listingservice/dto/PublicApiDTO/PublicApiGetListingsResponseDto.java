package com.exercise.listingservice.dto.PublicApiDTO;

import java.util.List;

public class PublicApiGetListingsResponseDto {
    private boolean result;
    private List<PublicApiListingDto> listings;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<PublicApiListingDto> getListings() {
        return listings;
    }

    public void setListings(List<PublicApiListingDto> listings) {
        this.listings = listings;
    }
    
}
