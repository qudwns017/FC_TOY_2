package org.example.kdtbe8_toyproject2.accomodation.service;

import org.example.kdtbe8_toyproject2.accomodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accomodation.model.AccommodationDto;
import org.springframework.stereotype.Component;

@Component
public class AccommodationConverter {

    public AccommodationDto toAccomodationDto(AccommodationEntity accommodationEntity){
        return AccommodationDto.builder()
                .tripId(accommodationEntity.getTripId())
                .id(accommodationEntity.getId())
                .tripId(accommodationEntity.getTripId())
                .name(accommodationEntity.getName())
                .checkInDatetime(accommodationEntity.getCheckInDatetime())
                .checkOutDatetime(accommodationEntity.getCheckOutDatetime())
                .build();
    }
}
