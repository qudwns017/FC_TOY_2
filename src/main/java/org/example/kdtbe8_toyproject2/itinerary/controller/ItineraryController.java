package org.example.kdtbe8_toyproject2.itinerary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping
    public ResponseEntity<?> findByTripId(
            @PathVariable Long tripId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.findByTripId(tripId));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @PathVariable Long tripId,
            @Validated
            @RequestBody
            ItineraryRequest itineraryRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(itineraryService.create(itineraryRequest,tripId));
    }

    @PutMapping("/{itineraryId}")
    public ResponseEntity<?> update(
            @PathVariable
            Long itineraryId,
            @Valid
            @RequestBody
            ItineraryRequest itineraryRequest
    ) {
        itineraryService.update(itineraryId, itineraryRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{itineraryId}")
    public ResponseEntity<?> delete(
            @PathVariable Long itineraryId
    ) {
        itineraryService.delete(itineraryId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
