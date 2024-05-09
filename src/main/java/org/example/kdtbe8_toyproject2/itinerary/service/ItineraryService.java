package org.example.kdtbe8_toyproject2.itinerary.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ItineraryError;
import org.example.kdtbe8_toyproject2.itinerary.db.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryMapper itineraryMapper;

    public List<ItineraryDto> findByTripId(Long tripId) {
        List<ItineraryEntity> itineraries;
        try {
            itineraries = itineraryMapper.findAllItineraries(tripId);
        }
        catch (Exception e){
            throw ItineraryError.TRIP_NOT_EXIST.defaultException(e);
        }

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
            throw ItineraryError.TRIP_NOT_EXIST.defaultException(e);
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
    public void update(
            Long id,
            ItineraryRequest itineraryRequest
    ) {
        var itineraryEntity = ItineraryEntity.builder()
                .id(id)
                .name(itineraryRequest.getItineraryName())
                .type(itineraryRequest.getType().getValue())
                .startDatetime(itineraryRequest.getStartDatetime())
                .endDatetime(itineraryRequest.getEndDatetime())
                .comment(itineraryRequest.getComment())
                .build()
                ;
        int itineraryUpdateSuccess = itineraryMapper.updateItinerary(itineraryEntity);

        if (itineraryUpdateSuccess == 0) {
            throw ItineraryError.ITINERARY_NOT_EXIST.defaultException();
        }


        int deleteMoveStatus = itineraryMapper.deleteMove(id);;
        int deleteStayStatus = itineraryMapper.deleteStay(id);

        if (deleteMoveStatus == 0 && deleteStayStatus == 0) {
            throw ItineraryError.UPDATE_FAILED.defaultException();
        }


        if(itineraryRequest.getType().getValue() == 0) {
            var moveEntity = MoveEntity.builder()
                    .itineraryId(id)
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build()
                    ;
            itineraryMapper.createMove(moveEntity);

        } else {
            var stayEntity = StayEntity.builder()
                    .itineraryId(id)
                    .place(itineraryRequest.getPlace())
                    .build()
                    ;
            itineraryMapper.createStay(stayEntity);
        }
    }

    @Transactional
    public void delete(Long itineraryId){
        if(itineraryMapper.deleteItinerary(itineraryId) == 0){
            throw ItineraryError.ITINERARY_NOT_EXIST.defaultException();
        }
    }
}
