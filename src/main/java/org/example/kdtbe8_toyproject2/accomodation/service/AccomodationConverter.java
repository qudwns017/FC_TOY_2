package org.example.kdtbe8_toyproject2.accomodation.service;

import org.example.kdtbe8_toyproject2.accomodation.db.AccomodationEntity;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationDTO;

public class AccomodationConverter {

    public AccomodationDTO toAccomodationDto(AccomodationEntity accomodationEntity){
        return AccomodationDTO.builder()
                .tripId(accomodationEntity.getTripId())
                .accomodationId(accomodationEntity.getAccomodationId())
                .tripId(accomodationEntity.getTripId())
                .name(accomodationEntity.getName())
                .checkInDatetime(accomodationEntity.getCheckInDatetime())
                .checkOutDatetime(accomodationEntity.getCheckOutDatetime())
                .build();
    }
}
