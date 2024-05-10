package org.example.kdtbe8_toyproject2.itinerary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.util.ApiResponse;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping
    public ApiResponse<?> findByTripId(
            @PathVariable Long tripId
    ) {
        return ApiResponse.<List<ItineraryDto>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(itineraryService.findByTripId(tripId))
                .build();
    }

    @PostMapping
    public ApiResponse<?> create(
            @PathVariable Long tripId,
            @Validated
            @RequestBody
            ItineraryRequest itineraryRequest
    ){
        return ApiResponse.<ItineraryDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(itineraryService.create(itineraryRequest,tripId))
                .build();
    }

    @PutMapping("/{itineraryId}")
    public ApiResponse<?> update(
            @PathVariable
            Long tripId,
            @PathVariable
            Long itineraryId,
            @Validated
            @RequestBody
            ItineraryRequest itineraryRequest
    ) {
        return ApiResponse.<ItineraryDto>builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .data(itineraryService.update(tripId, itineraryId, itineraryRequest))
                .build();
    }

    @DeleteMapping("/{itineraryId}")
    public ApiResponse<?> delete(
            @PathVariable Long tripId,
            @PathVariable Long itineraryId
    ) {
        itineraryService.delete(tripId, itineraryId);

        return ApiResponse.builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}
