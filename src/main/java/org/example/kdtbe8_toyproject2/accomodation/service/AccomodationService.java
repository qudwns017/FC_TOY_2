package org.example.kdtbe8_toyproject2.accomodation.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.accomodation.db.AccomodationEntity;
import org.example.kdtbe8_toyproject2.accomodation.db.AccomodationMapper;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationDTO;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccomodationService {

    private final AccomodationMapper accomodationMapper;
    private final AccomodationConverter accomodationConverter;

    public void create(Long tripId, AccomodationRequest accomodationRequest) {
        //var TripEntity = tripMapper.findById(accomodationRequest.getTripId()).get();
        var entity = AccomodationEntity.builder()
                .tripId(accomodationRequest.getTripId())
                .id(accomodationRequest.getId())
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();
        var saveEntity = accomodationMapper.create(entity);

    }

    public void delete(Long tripId, Long id) {
        //var entity = accomodationMapper.findByTripId(tripId)
         accomodationMapper.delete(tripId,id);
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