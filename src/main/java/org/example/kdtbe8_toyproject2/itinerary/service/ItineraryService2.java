package org.example.kdtbe8_toyproject2.itinerary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.mapper.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItineraryService2 {
    private final ItineraryMapper itineraryMapper;

    public void create(
            ItineraryRequest itineraryRequest,
            Long tripId
    ){
        var itineraryEntity = ItineraryEntity.builder()
                .tripId(tripId)
                .itineraryName(itineraryRequest.getItineraryName())
                .type(itineraryRequest.getType())
                .startDateTime(itineraryRequest.getStartDate())
                .endDateTime(itineraryRequest.getEndDate())
                .comment(itineraryRequest.getComment())
                .build();
        var saveEntity = itineraryMapper.createItinerary(itineraryEntity);

        if(itineraryRequest.getType() == 0){
            var moveEntity = MoveEntity.builder()
                    .itineraryId(saveEntity)
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build();
            itineraryMapper.createMove(moveEntity);
        }

        else{
            var stayEntity = StayEntity.builder()
                    .itineraryId(saveEntity)
                    .place(itineraryRequest.getPlace())
                    .build();
            itineraryMapper.createStay(stayEntity);
        }
    }
    public void delete(Long itineraryId){
        itineraryMapper.deleteItinerary(itineraryId);
    }
}
