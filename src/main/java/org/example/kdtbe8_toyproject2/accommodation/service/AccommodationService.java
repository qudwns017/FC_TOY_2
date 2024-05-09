package org.example.kdtbe8_toyproject2.accommodation.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccomodationRequest;
import org.example.kdtbe8_toyproject2.global.error.errorcode.AccommodationError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationMapper accommodationMapper;

    public AccommodationDto create(Long tripId, @Valid AccomodationRequest accomodationRequest) {
        var entity = AccommodationEntity.builder()
                .tripId(tripId)
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();

        try {
            accommodationMapper.create(entity);
        } catch (Exception e) {
            throw AccommodationError.TRIP_NOT_EXIST.defaultException(e);
        }

        return AccommodationDto.toAccommodationDto(entity);
    }

    public void delete(Long id, Long tripId) {
        var accommodationEntity = accommodationMapper.findByTripId(tripId);
        if (accommodationMapper.delete(id) == 0) {
            throw AccommodationError.ACCOMMODATION_NOT_EXIST.defaultException();

        }
    }

    public List<AccommodationDto> findByTripId(Long tripId){
       List<AccommodationEntity> accomodationList = accommodationMapper.findByTripId(tripId);
       if(accomodationList == null || accomodationList.isEmpty()){
           throw AccommodationError.ACCOMMODATION_NOT_EXIST.defaultException();
       }

       return accomodationList.stream()
               .map(AccommodationDto::toAccommodationDto)
               .collect(Collectors.toList());
    }
}
