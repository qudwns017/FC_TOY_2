package org.example.kdtbe8_toyproject2.accommodation.service;

import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.springframework.stereotype.Component;

@Component
public class AccommodationConverter {

    public AccommodationDto toAccomodationDto(AccommodationEntity accommodationEntity){
        return AccommodationDto.builder()
                .tripId(accommodationEntity.getTripId())
                .id(accommodationEntity.getId())
                .name(accommodationEntity.getName())
                .checkInDatetime(accommodationEntity.getCheckInDatetime())
                .checkOutDatetime(accommodationEntity.getCheckOutDatetime())
                .build();
    }
}
