package org.example.kdtbe8_toyproject2.itinerary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.model.request.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.model.dto.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.service.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping("/all")
    public ResponseEntity<?> findByTripId(
            @PathVariable Long tripId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.findByTripId(tripId));
    }

    @PostMapping("")
    public ResponseEntity<?> create(
            @PathVariable Long tripId,
            @Validated
            @RequestBody
            ItineraryRequest itineraryRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(itineraryService.create(itineraryRequest,tripId));
    }

    @DeleteMapping("/{itineraryId}")
    public ResponseEntity<?> delete(
            @PathVariable Long itineraryId
    ) {
        int result = itineraryService.delete(itineraryId);

        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            ItineraryRequest itineraryRequest
    ) {
        int result = itineraryService.update(id, itineraryRequest);

        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
