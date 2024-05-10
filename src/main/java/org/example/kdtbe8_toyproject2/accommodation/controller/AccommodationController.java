package org.example.kdtbe8_toyproject2.accommodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationRequest;
import org.example.kdtbe8_toyproject2.accommodation.service.AccommodationService;
import org.example.kdtbe8_toyproject2.global.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    @GetMapping("")
    public ApiResponse<?> findByTripId(@PathVariable Long tripId){
        return ApiResponse.<List<AccommodationDto>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.findByTripId(tripId))
                .build();
    }
    @PostMapping("")
    public ApiResponse<?> create(@PathVariable Long tripId, @Valid @RequestBody AccommodationRequest accommodationRequest){
        return ApiResponse.<AccommodationDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.create(tripId, accommodationRequest))
                .build();
    }

    @DeleteMapping("/{itineraryId}")
    public ApiResponse<?> delete(@PathVariable Long tripId,@PathVariable Long itineraryId){
        accommodationService.delete(tripId, itineraryId);
        return ApiResponse.builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}