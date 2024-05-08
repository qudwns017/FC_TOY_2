package org.example.kdtbe8_toyproject2.accomodation.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
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

    public void create(Long tripId, AccomodationRequest accomodationRequest) throws Exception {
        //var TripEntity = tripMapper.findById(accomodationRequest.getTripId()).get();
        List<AccomodationEntity> accomodationEntityList = accomodationMapper.findByTripId(tripId);
        if( accomodationEntityList == null || accomodationEntityList.isEmpty()){
            throw new Exception("해당 tripId가 존재하지 않습니다 :" + tripId);
        }
        var entity = AccomodationEntity.builder()
                .tripId(accomodationRequest.getTripId())
                .id(accomodationRequest.getId())
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();

        var saveEntity = accomodationMapper.create(entity);

    }

    public void delete(Long tripId, Long id) throws Exception {
        List<AccomodationEntity> accomodationEntityList = accomodationMapper.findByTripId(tripId);
        if(accomodationEntityList == null || accomodationEntityList.isEmpty()){
            throw new Exception("해당 tripId가 존재하지 않습니다 :" + tripId);
        }
        if (accomodationEntityList.stream().noneMatch(entity -> entity.getId().equals(id))) {
            throw new Exception("해당 숙박id가 존재하지 않습니다 :" + id);
        }
         accomodationMapper.delete(tripId,id);
    }



    public List<AccomodationDTO> findByTripId(Long tripId) throws NotFoundException {
       List<AccomodationEntity> accomodationList = accomodationMapper.findByTripId(tripId);
       if(accomodationList == null || accomodationList.isEmpty()){
           throw new NotFoundException("존재하지 않는 여행 id" + tripId);
       }
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