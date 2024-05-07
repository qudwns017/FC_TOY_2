package org.example.kdtbe8_toyproject2.itinerary.service.converter;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.model.dto.ItineraryDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItineraryConverter {

    public ItineraryDto toDto(ItineraryEntity itineraryEntity, MoveEntity moveEntity, StayEntity stayEntity) {
        return ItineraryDto.builder()
                .id(itineraryEntity.getId())
                .tripId(itineraryEntity.getTripId())
                .name(itineraryEntity.getName())
                .type(itineraryEntity.getType())
                .startDatetime(itineraryEntity.getStartDatetime())
                .endDatetime(itineraryEntity.getEndDatetime())
                .comment(itineraryEntity.getComment())
                .transportation(moveEntity != null ? moveEntity.getTransportation() : null)
                .departurePlace(moveEntity != null ? moveEntity.getDeparturePlace() : null)
                .arrivalPlace(moveEntity != null ? moveEntity.getArrivalPlace() : null)
                .place(stayEntity != null ? stayEntity.getPlace() : null)
                .build();
    }

}
