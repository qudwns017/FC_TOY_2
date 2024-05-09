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
            throw ItineraryError.USERNAME_ALREADY_EXISTS.defaultException(e);
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
    public int delete(Long itineraryId){
        var itineraryEntity = itineraryMapper.findItineraryById(itineraryId);
        int deleteItineraryStatus = itineraryMapper.deleteItinerary(itineraryId);

        if (deleteItineraryStatus == 0){
            return deleteItineraryStatus;
        }

        if(itineraryEntity.getType() == 0){
            return itineraryMapper.deleteMove(itineraryId);
        }
        else{
            return itineraryMapper.deleteStay(itineraryId);
        }
    }

    @Transactional
    public int update(
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
            return itineraryUpdateSuccess;
        }

        int deleteMoveStatus = 0;
        int deleteStayStatus = 0;
        int updateStatus = 0;

        if(itineraryRequest.getType().getValue() == 0) {
            var moveEntity = MoveEntity.builder()
                    .itineraryId(id)
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build()
                    ;

            deleteMoveStatus = itineraryMapper.deleteMove(id);
            deleteStayStatus = itineraryMapper.deleteStay(id);
            updateStatus = itineraryMapper.createMove(moveEntity);

        } else {
            var stayEntity = StayEntity.builder()
                    .itineraryId(id)
                    .place(itineraryRequest.getPlace())
                    .build()
                    ;
            deleteStayStatus = itineraryMapper.deleteStay(id);
            deleteMoveStatus = itineraryMapper.deleteMove(id);
            updateStatus = itineraryMapper.createStay(stayEntity);
        }

        return (deleteStayStatus & deleteMoveStatus & updateStatus);
    }
}
