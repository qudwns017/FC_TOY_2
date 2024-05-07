package org.example.kdtbe8_toyproject2.itinerary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.ItineraryService2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ItineraryController2 {
    private final ItineraryService2 itineraryService2;

    @PostMapping("")
    public void create(
            @PathVariable Long tripId,
            @Valid
            @RequestBody
            ItineraryRequest itineraryRequest
    ){
        itineraryService2.create(itineraryRequest,tripId);
    }
    @DeleteMapping("/{itineraryId}")
    public void delete(
            @PathVariable Long itineraryId
    ){

    }
}
