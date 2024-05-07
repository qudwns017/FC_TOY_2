package org.example.accomodation.service;

import org.example.accomodation.db.AccomodationEntity;
import org.example.accomodation.model.AccomodationDTO;

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
