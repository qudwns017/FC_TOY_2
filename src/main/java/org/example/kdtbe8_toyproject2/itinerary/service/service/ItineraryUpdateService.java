package org.example.kdtbe8_toyproject2.itinerary.service.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.db.mapper.ItineraryMapper;
import org.example.kdtbe8_toyproject2.itinerary.model.request.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.converter.ItineraryConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItineraryUpdateService {

    private final ItineraryMapper itineraryMapper;
    private final ItineraryConverter itineraryConverter;

    public void update(
            Long tripId,
            Long id,
            ItineraryRequest itineraryRequest
    ) {

    }
}
