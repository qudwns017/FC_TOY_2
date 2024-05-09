package org.example.kdtbe8_toyproject2.accommodation.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accommodation.Api;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccomodationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationMapper accommodationMapper;

    public AccommodationDto create(Long tripId, @Valid AccomodationRequest accomodationRequest)  {
        List<AccommodationEntity> accommodationEntityList = accommodationMapper.findByTripId(tripId);
        //존재 하지 않는 trip id SQLIntegrityConstraintViolationException
        var entity = AccommodationEntity.builder()
                .tripId(accomodationRequest.getTripId())
                .id(accomodationRequest.getId())
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();

        accommodationMapper.create(entity);
        return AccommodationDto.toAccommodationDto(entity);
    }

    public int delete(Long tripId, Long id) throws Exception {// 반환타입 수정
        List<AccommodationEntity> accommodationEntityList = accommodationMapper.findByTripId(tripId);

        if(accommodationEntityList == null & accommodationEntityList.isEmpty()){
            throw new Exception("해당 tripId가 존재하지 않습니다 :" + tripId);
        }
        if (accommodationEntityList.stream().noneMatch(entity -> entity.getId().equals(id))) {
            throw new Exception("해당 숙박id가 존재하지 않습니다 :" + id);
        }

        accommodationMapper.delete(tripId,id);
        return accommodationMapper.delete(tripId,id);
    }



    public List<AccommodationDto> findByTripId(Long tripId) throws NotFoundException {
       List<AccommodationEntity> accomodationList = accommodationMapper.findByTripId(tripId);
       if(accomodationList == null || accomodationList.isEmpty()){
           throw new NotFoundException("존재하지 않는 여행 id" + tripId);
       }
       return accomodationList.stream()
               .map(AccommodationDto::toAccommodationDto)
               .collect(Collectors.toList());

    }

}
