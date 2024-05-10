package org.example.kdtbe8_toyproject2.trip.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.global.util.ApiResponse;
import org.example.kdtbe8_toyproject2.global.validation.Conditional;
import org.example.kdtbe8_toyproject2.trip.db.GetTripByIdEntity;
import org.example.kdtbe8_toyproject2.trip.db.TripListEntity;
import org.example.kdtbe8_toyproject2.trip.db.TripMapper;
import org.example.kdtbe8_toyproject2.trip.model.TripDto;
import org.example.kdtbe8_toyproject2.trip.model.TripRequest;
import org.example.kdtbe8_toyproject2.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/trips")
@RequiredArgsConstructor

@RestController
public class TripController {
    private final TripService tripService;

    @GetMapping
    public ApiResponse<?> getTripList() {
        return ApiResponse.<List<TripListEntity>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.findAll())
                .build();
    }

    @GetMapping("/{tripId}")
    public ApiResponse<?> getTripById(
            @PathVariable Long tripId
    ) {
        return ApiResponse.<GetTripByIdEntity>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.findById(tripId))
                .build();
    }

    @PostMapping
    public ApiResponse<?> create(
            @Valid @RequestBody TripRequest tripRequest
    ) {
        return ApiResponse.<TripDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.create(tripRequest))
                .build();
    }

    @PutMapping("/{tripId}")
    public ApiResponse<?> update(@PathVariable Long tripId, @Valid @RequestBody TripRequest tripRequest) {
        return ApiResponse.<TripDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.update(tripId, tripRequest))
                .build();
    }

    @DeleteMapping("/{tripId}")
    public ApiResponse<?> delete(@PathVariable Long tripId) {
        tripService.delete(tripId);
        return ApiResponse.<TripDto>builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}
