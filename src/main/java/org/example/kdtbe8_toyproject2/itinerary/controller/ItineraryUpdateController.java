package org.example.kdtbe8_toyproject2.itinerary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.model.request.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.service.ItineraryService;
import org.example.kdtbe8_toyproject2.itinerary.service.service.ItineraryUpdateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@RequiredArgsConstructor
public class ItineraryUpdateController {
    private final ItineraryUpdateService itineraryUpdateService;

    @PutMapping("/{id}")
    public void update(
            @PathVariable
            Long tripId,
            Long id,
            @Valid
            @RequestBody
            ItineraryRequest itineraryRequest
            ) {
        itineraryUpdateService.update(tripId, id, itineraryRequest);
    }
}
