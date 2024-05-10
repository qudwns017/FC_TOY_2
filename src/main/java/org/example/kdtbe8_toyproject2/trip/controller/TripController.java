package org.example.kdtbe8_toyproject2.trip.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.util.ApiResponseUtil;
import org.example.kdtbe8_toyproject2.trip.db.GetTripByIdEntity;
import org.example.kdtbe8_toyproject2.trip.db.TripListEntity;
import org.example.kdtbe8_toyproject2.trip.model.TripDto;
import org.example.kdtbe8_toyproject2.trip.model.TripRequest;
import org.example.kdtbe8_toyproject2.trip.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/trips")
@RequiredArgsConstructor

@RestController
public class TripController {
    private final TripService tripService;

    @GetMapping
    public ApiResponseUtil<?> getTripList() {
        return ApiResponseUtil.<List<TripListEntity>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.findAll())
                .build();
    }

    @GetMapping("/{tripId}")
    public ApiResponseUtil<?> getTripById(
            @PathVariable Long tripId
    ) {
        return ApiResponseUtil.<GetTripByIdEntity>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.findById(tripId))
                .build();
    }

    @PostMapping
    public ApiResponseUtil<?> create(
            @Valid @RequestBody TripRequest tripRequest
    ) {
        return ApiResponseUtil.<TripDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.create(tripRequest))
                .build();
    }

    @PutMapping("/{tripId}")
    public ApiResponseUtil<?> update(@PathVariable Long tripId, @Valid @RequestBody TripRequest tripRequest) {
        return ApiResponseUtil.<TripDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.update(tripId, tripRequest))
                .build();
    }

    @DeleteMapping("/{tripId}")
    public ApiResponseUtil<?> delete(@PathVariable Long tripId) {
        tripService.delete(tripId);
        return ApiResponseUtil.<TripDto>builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}
