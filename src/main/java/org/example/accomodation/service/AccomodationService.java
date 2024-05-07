package org.example.accomodation.service;

import lombok.RequiredArgsConstructor;
import org.example.accomodation.db.AccomodationEntity;
import org.example.accomodation.db.AccomodationMapper;
import org.example.accomodation.model.AccomodationDTO;
import org.example.accomodation.model.AccomodationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccomodationService {

    private final AccomodationMapper accomodationMapper;
    private final AccomodationConverter accomodationConverter;

    public void create(Long tripId,AccomodationRequest accomodationRequest) {
        var entity = AccomodationEntity.builder()
                .tripId(accomodationRequest.getTripId())
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



    public List<AccomodationDTO> findByTripId(Long tripId){
       List<AccomodationEntity> accomodationList = accomodationMapper.findByTripId(tripId);
       return accomodationList.stream()
               .map(accomodationConverter::toAccomodationDto)
               .collect(Collectors.toList());

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