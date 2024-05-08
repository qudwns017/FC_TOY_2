package org.example.kdtbe8_toyproject2.itinerary.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.mapper.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.request.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.converter.ItineraryConverter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItineraryUpdateService {

    private final ItineraryMapper itineraryMapper;
    private final ItineraryConverter itineraryConverter;

    public void update(
            Long id,
            ItineraryRequest itineraryRequest
    ) {
        var itineraryEntity = ItineraryEntity.builder()
                .id(id)
                .name(itineraryRequest.getItineraryName())
                .type(itineraryRequest.getType().getValue())
                .startDatetime(itineraryRequest.getStartDate())
                .endDatetime(itineraryRequest.getEndDate())
                .comment(itineraryRequest.getComment())
                .build()
                ;
        itineraryMapper.updateItinerary(itineraryEntity);
        log.info("id: " + id);

        if(itineraryRequest.getType().equals(0)) {
            var moveEntity = MoveEntity.builder()
                    .itineraryId(id)
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build()
                    ;
            itineraryMapper.deleteMove(id);
            itineraryMapper.deleteStay(id);
            itineraryMapper.createMove(moveEntity);
        } else {
            var stayEntity = StayEntity.builder()
                    .itineraryId(id)
                    .place(itineraryRequest.getPlace())
                    .build()
                    ;
            itineraryMapper.deleteStay(id);
            itineraryMapper.deleteMove(id);
            itineraryMapper.createStay(stayEntity);
        }
    }
}
