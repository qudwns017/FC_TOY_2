package org.example.kdtbe8_toyproject2.itinerary.service.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.mapper.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.model.dto.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.service.converter.ItineraryConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryMapper itineraryMapper;
    private final ItineraryConverter itineraryConverter;

    public List<ItineraryDto> findByTripId(Long tripId) {
        List<ItineraryEntity> itineraries = itineraryMapper.findAllItineraries(tripId);

        return itineraries.stream()
                .map(this::mapToItineraryDto)
                .collect(Collectors.toList());
    }


    private ItineraryDto mapToItineraryDto(ItineraryEntity itineraryEntity) {
        MoveEntity moveEntity = itineraryEntity.getType().equals(0) ? itineraryMapper.findMoveById(itineraryEntity.getId()) : null;
        StayEntity stayEntity = itineraryEntity.getType().equals(1) ? itineraryMapper.findStayById(itineraryEntity.getId()) : null;

        return itineraryConverter.toDto(itineraryEntity, moveEntity, stayEntity);
    }

    public void create(
            ItineraryRequest itineraryRequest,
            Long tripId
    ){
        var itineraryEntity = ItineraryEntity.builder()
                .tripId(tripId)
                .name(itineraryRequest.getItineraryName())
                .type(itineraryRequest.getType())
                .startDatetime(itineraryRequest.getStartDate())
                .endDatetime(itineraryRequest.getEndDate())
                .comment(itineraryRequest.getComment())
                .build();
        var saveEntity = itineraryMapper.createItinerary(itineraryEntity);

        if(itineraryRequest.getType() == 0){
            var moveEntity = MoveEntity.builder()
                    .itineraryId(itineraryEntity.getId())
                    .transportation(itineraryRequest.getTransportation())
                    .departurePlace(itineraryRequest.getDeparturePlace())
                    .arrivalPlace(itineraryRequest.getArrivalPlace())
                    .build();
            itineraryMapper.createMove(moveEntity);
        }

        else{
            var stayEntity = StayEntity.builder()
                    .itineraryId(itineraryEntity.getId())
                    .place(itineraryRequest.getPlace())
                    .build();
            itineraryMapper.createStay(stayEntity);
        }

    }
}
