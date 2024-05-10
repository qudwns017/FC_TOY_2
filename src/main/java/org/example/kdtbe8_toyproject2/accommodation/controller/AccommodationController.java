package org.example.kdtbe8_toyproject2.accommodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationRequest;
import org.example.kdtbe8_toyproject2.accommodation.service.AccommodationService;
import org.example.kdtbe8_toyproject2.global.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    @GetMapping("")
    public ApiResponseUtil<?> findByTripId(@PathVariable Long tripId){
        return ApiResponseUtil.<List<AccommodationDto>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.findByTripId(tripId))
                .build();
    }
    @PostMapping("")
    public ApiResponseUtil<?> create(@PathVariable Long tripId, @Valid @RequestBody AccommodationRequest accommodationRequest){
        return ApiResponseUtil.<AccommodationDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.create(tripId, accommodationRequest))
                .build();
    }

    @DeleteMapping("/{itineraryId}")
    public ApiResponseUtil<?> delete(@PathVariable Long tripId, @PathVariable Long itineraryId){
        accommodationService.delete(tripId, itineraryId);
        return ApiResponseUtil.builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}