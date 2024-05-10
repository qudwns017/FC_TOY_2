package org.example.kdtbe8_toyproject2.accommodation.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationRequest;
import org.example.kdtbe8_toyproject2.global.error.errorcode.TravelError;
import org.example.kdtbe8_toyproject2.trip.service.TripService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationMapper accommodationMapper;
    private final TripService tripService;

    public List<AccommodationDto> findByTripId(Long tripId){
        Long tripServiceId = tripService.findTripId(tripId);
        if(tripServiceId == null){
            throw TravelError.TRIP_NOT_EXIST.defaultException();
            }

        List<AccommodationEntity> accommodations = accommodationMapper.findByTripId(tripId);

        return accommodations.stream()
                .map(AccommodationDto::toAccommodationDto)
                .collect(Collectors.toList());
    }

    public AccommodationDto create(Long tripId, @Valid AccommodationRequest accommodationRequest) {
        var accommodationEntity = AccommodationEntity.builder()
                .tripId(tripId)
                .name(accommodationRequest.getName())
                .checkInDatetime(accommodationRequest.getCheckInDatetime())
                .checkOutDatetime(accommodationRequest.getCheckOutDatetime())
                .build();

        try {
            accommodationMapper.create(accommodationEntity);
        } catch (Exception e) {
            throw TravelError.TRIP_NOT_EXIST.defaultException(e);
        }

        return AccommodationDto.toAccommodationDto(accommodationEntity);
    }

    public void delete(Long tripId, Long itineraryId) {
        if(tripService.findTripId(tripId) == null){
            throw TravelError.TRIP_NOT_EXIST.defaultException();
        }
        if (accommodationMapper.delete(itineraryId) == 0) {
            throw TravelError.ACCOMMODATION_NOT_EXIST.defaultException();

        }
    }
}
