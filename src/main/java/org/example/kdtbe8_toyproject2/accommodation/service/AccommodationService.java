package org.example.kdtbe8_toyproject2.accommodation.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccomodationRequest;
import org.example.kdtbe8_toyproject2.global.error.errorcode.TravelError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationMapper accommodationMapper;

    public List<AccommodationDto> findByTripId(Long tripId){
        List<AccommodationEntity> accommodations = accommodationMapper.findByTripId(tripId);
        if(accommodations == null || accommodations.isEmpty()){
            throw TravelError.ACCOMMODATION_NOT_EXIST.defaultException();
        }

        return accommodations.stream()
                .map(AccommodationDto::toAccommodationDto)
                .collect(Collectors.toList());
    }

    public AccommodationDto create(Long tripId, @Valid AccomodationRequest accomodationRequest) {
        var accommodationEntity = AccommodationEntity.builder()
                .tripId(tripId)
                .name(accomodationRequest.getName())
                .checkInDatetime(accomodationRequest.getCheckInDatetime())
                .checkOutDatetime(accomodationRequest.getCheckOutDatetime())
                .build();

        try {
            accommodationMapper.create(accommodationEntity);
        } catch (Exception e) {
            throw TravelError.TRIP_NOT_EXIST.defaultException(e);
        }

        return AccommodationDto.toAccommodationDto(accommodationEntity);
    }

    public void delete(Long id, Long tripId) {
        var accommodationEntity = accommodationMapper.findByTripId(tripId);
        if (accommodationEntity == null || accommodationEntity.isEmpty()) {
            throw TravelError.TRIP_NOT_EXIST.defaultException();
        }
        if (accommodationMapper.delete(id) == 0) {
            throw TravelError.ACCOMMODATION_NOT_EXIST.defaultException();

        }
    }
}
