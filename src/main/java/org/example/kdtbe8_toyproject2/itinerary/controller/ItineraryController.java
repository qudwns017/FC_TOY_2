package org.example.kdtbe8_toyproject2.itinerary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.model.dto.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.service.service.ItineraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping("/all")
    public List<ItineraryDto> findByTripId(
            @PathVariable Long tripId
    ) {
        return itineraryService.findByTripId(tripId);
    }
    @PostMapping("")
    public void create(
            @PathVariable Long tripId,
            @Valid
            @RequestBody
            ItineraryRequest itineraryRequest
    ){
        itineraryService.create(itineraryRequest,tripId);
    }
}
