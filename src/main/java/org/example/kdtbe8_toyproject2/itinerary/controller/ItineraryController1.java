package org.example.kdtbe8_toyproject2.itinerary.controller;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.model.dto.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.service.service.ItineraryService1;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ItineraryController1 {

    private final ItineraryService1 itineraryService1;

    @GetMapping("/all")
    public List<ItineraryDto> findByTripId(
            @PathVariable Long trip_id
    ) {
        return itineraryService1.findByTripId(trip_id);
    }
}
