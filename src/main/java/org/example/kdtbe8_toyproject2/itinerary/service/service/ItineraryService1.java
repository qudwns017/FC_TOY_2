package org.example.kdtbe8_toyproject2.itinerary.service.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.mapper.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.dto.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.service.converter.ItineraryConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryService1 {

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
}
