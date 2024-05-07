package org.example.kdtbe8_toyproject2.accomodation.service;

import org.example.kdtbe8_toyproject2.accomodation.db.AccomodationEntity;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationDTO;
import org.springframework.stereotype.Component;

@Component
public class AccomodationConverter {

    public AccomodationDTO toAccomodationDto(AccomodationEntity accomodationEntity){
        return AccomodationDTO.builder()
                .tripId(accomodationEntity.getTripId())
                .id(accomodationEntity.getId())
                .tripId(accomodationEntity.getTripId())
                .name(accomodationEntity.getName())
                .checkInDatetime(accomodationEntity.getCheckInDatetime())
                .checkOutDatetime(accomodationEntity.getCheckOutDatetime())
                .build();
    }
}
