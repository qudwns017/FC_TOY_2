package org.example.kdtbe8_toyproject2.itinerary.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.errorcode.TravelError;
import org.example.kdtbe8_toyproject2.itinerary.db.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryDto;
import org.example.kdtbe8_toyproject2.trip.service.TripService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryMapper itineraryMapper;
    private final TripService tripService;

    public List<ItineraryDto> findByTripId(Long tripId) {
        if(tripService.findTripId(tripId) == null){
            throw TravelError.TRIP_NOT_EXIST.defaultException();
        }

        List<ItineraryEntity> itineraries = itineraryMapper.findAllItineraries(tripId);

        return itineraries.stream()
                .map(this::mapToItineraryDto)
                .collect(Collectors.toList());
    }


    private ItineraryDto mapToItineraryDto(ItineraryEntity itineraryEntity) {
        MoveEntity moveEntity = itineraryEntity.getType().equals(0) ? itineraryMapper.findMoveById(itineraryEntity.getId()) : null;
        StayEntity stayEntity = itineraryEntity.getType().equals(1) ? itineraryMapper.findStayById(itineraryEntity.getId()) : null;

        return ItineraryDto.toDto(itineraryEntity, moveEntity, stayEntity);
    }

    @Transactional
    public ItineraryDto create(
            ItineraryRequest itineraryRequest,
            Long tripId
    ){
        var trip = tripService.findById(tripId);
        if(!isValidDateTime(
                trip.getStartDate(), trip.getEndDate(), itineraryRequest.getStartDatetime(), itineraryRequest.getEndDatetime()
        )){
            throw TravelError.TIME_ERROR.defaultException();
        }

        ItineraryEntity itineraryEntity = ItineraryEntity.builder()
                .tripId(tripId)
                .name(itineraryRequest.getItineraryName())
                .type(itineraryRequest.getType().getValue())
                .startDatetime(itineraryRequest.getStartDatetime())
                .endDatetime(itineraryRequest.getEndDatetime())
                .comment(itineraryRequest.getComment())
                .build();
        try {
            itineraryMapper.createItinerary(itineraryEntity);
        }
        catch (Exception e){
            throw TravelError.TRIP_NOT_EXIST.defaultException(e);
        }


        MoveEntity moveEntity = null;
        StayEntity stayEntity = null;

        if(itineraryRequest.getType().getValue() == 0){
            moveEntity = MoveEntity.builder()
                    .itineraryId(itineraryEntity.getId())
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build();
            itineraryMapper.createMove(moveEntity);
        }

        else{
            stayEntity = StayEntity.builder()
                    .itineraryId(itineraryEntity.getId())
                    .place(itineraryRequest.getPlace())
                    .build();
            itineraryMapper.createStay(stayEntity);
        }

        return ItineraryDto.toDto(itineraryEntity, moveEntity, stayEntity);
    }

    @Transactional
    public ItineraryDto update(
            Long tripId,
            Long id,
            ItineraryRequest itineraryRequest
    ) {

        var trip = tripService.findById(tripId);
        if(!isValidDateTime(
                trip.getStartDate(), trip.getEndDate(), itineraryRequest.getStartDatetime(), itineraryRequest.getEndDatetime()
        ))
             throw TravelError.TIME_ERROR.defaultException();

        var itineraryEntity = ItineraryEntity.builder()
                .id(id)
                .tripId(tripId)
                .name(itineraryRequest.getItineraryName())
                .type(itineraryRequest.getType().getValue())
                .startDatetime(itineraryRequest.getStartDatetime())
                .endDatetime(itineraryRequest.getEndDatetime())
                .comment(itineraryRequest.getComment())
                .build()
                ;
        itineraryMapper.updateItinerary(itineraryEntity);

        int deleteMoveStatus = itineraryMapper.deleteMove(id);;
        int deleteStayStatus = itineraryMapper.deleteStay(id);

        if (deleteMoveStatus == 0 && deleteStayStatus == 0) {
            throw TravelError.UPDATE_FAILED.defaultException();
        }

        MoveEntity moveEntity = null;
        StayEntity stayEntity = null;

        if(itineraryRequest.getType().getValue() == 0) {
            moveEntity = MoveEntity.builder()
                    .itineraryId(id)
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build()
                    ;
            itineraryMapper.createMove(moveEntity);

        } else {
            stayEntity = StayEntity.builder()
                    .itineraryId(id)
                    .place(itineraryRequest.getPlace())
                    .build()
                    ;
            itineraryMapper.createStay(stayEntity);
        }
        return ItineraryDto.toDto(itineraryEntity, moveEntity, stayEntity);
    }

    @Transactional
    public void delete(Long tripId, Long itineraryId){
        if(tripService.findTripId(tripId) == null){
            throw TravelError.TRIP_NOT_EXIST.defaultException();
        }

        if(itineraryMapper.deleteItinerary(itineraryId) == 0){
            throw TravelError.ITINERARY_NOT_EXIST.defaultException();
        };
    }

    public static boolean isValidDateTime(
            LocalDate startTravel, LocalDate endTravel,
            LocalDateTime startItinerary, LocalDateTime endItinerary
    ) {
        return (startItinerary.toLocalDate().isEqual(startTravel) || startItinerary.toLocalDate().isAfter(startTravel)) &&
                (endItinerary.toLocalDate().isEqual(endTravel) || endItinerary.toLocalDate().isBefore(endTravel)) &&
                (startItinerary.isBefore(endItinerary));
    }

}
