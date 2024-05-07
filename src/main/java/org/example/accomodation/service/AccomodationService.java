package org.example.accomodation.service;

import lombok.RequiredArgsConstructor;
import org.example.accomodation.db.AccomodationEntity;
import org.example.accomodation.db.AccomodationMapper;
import org.example.accomodation.model.AccomodationDTO;
import org.example.accomodation.model.AccomodationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccomodationService {
    private final AccomodationMapper accomodationMapper;
    private final AccomodationConverter accomodationConverter;

    public void create(Long trip_id,AccomodationRequest accomodationRequest) {
        var entity = AccomodationEntity.builder()
                .tripId(trip_id)
                .accomodationId(accomodationRequest.getTripId())
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();
        var saveEntity = accomodationMapper.create(entity);

    }

    public void delete(Long tripId, Long accomodationId) {
         accomodationMapper.delete(tripId,accomodationId);
    }



    public AccomodationDTO findByTripId(Long tripId){
        var entity =  accomodationMapper.findByTripId(tripId);
        return accomodationMapper.toAccomodationDto(entity);
    }

}
/*
create()
findByTripId()
delete()
 */
/*  public List<AccomodationEntity> findByTripId(Long tripId, Long accomodationId){
        return accomodationMapper.findByTripId(tripId, accomodationId);
    }*/