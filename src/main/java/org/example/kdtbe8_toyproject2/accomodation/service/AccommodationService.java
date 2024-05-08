package org.example.kdtbe8_toyproject2.accomodation.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accomodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accomodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accomodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationMapper accommodationMapper;
    private final AccommodationConverter accommodationConverter;

    public void create(Long tripId, AccomodationRequest accomodationRequest) throws Exception {
        //var TripEntity = tripMapper.findById(accomodationRequest.getTripId()).get();
        List<AccommodationEntity> accommodationEntityList = accommodationMapper.findByTripId(tripId);
        if( accommodationEntityList == null || accommodationEntityList.isEmpty()){
            throw new Exception("해당 tripId가 존재하지 않습니다 :" + tripId);
        }
        var entity = AccommodationEntity.builder()
                .tripId(accomodationRequest.getTripId())
                .id(accomodationRequest.getId())
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();

        var saveEntity = accommodationMapper.create(entity);

    }

    public void delete(Long tripId, Long id) throws Exception {
        List<AccommodationEntity> accommodationEntityList = accommodationMapper.findByTripId(tripId);
        if(accommodationEntityList == null || accommodationEntityList.isEmpty()){
            throw new Exception("해당 tripId가 존재하지 않습니다 :" + tripId);
        }
        if (accommodationEntityList.stream().noneMatch(entity -> entity.getId().equals(id))) {
            throw new Exception("해당 숙박id가 존재하지 않습니다 :" + id);
        }
         accommodationMapper.delete(tripId,id);
    }



    public List<AccommodationDto> findByTripId(Long tripId) throws NotFoundException {
       List<AccommodationEntity> accomodationList = accommodationMapper.findByTripId(tripId);
       if(accomodationList == null || accomodationList.isEmpty()){
           throw new NotFoundException("존재하지 않는 여행 id" + tripId);
       }
       return accomodationList.stream()
               .map(accommodationConverter::toAccomodationDto)
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