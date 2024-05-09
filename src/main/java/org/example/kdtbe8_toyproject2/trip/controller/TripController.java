package org.example.kdtbe8_toyproject2.trip.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.trip.db.TripMapper;
import org.example.kdtbe8_toyproject2.trip.model.TripRequest;
import org.example.kdtbe8_toyproject2.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/trips")
@RequiredArgsConstructor

@RestController
public class TripController {
    private final TripService tripService;

    @GetMapping
    public ResponseEntity<?> getTripList() {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.findAll());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<?> getTripById(
            @PathVariable Long tripId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.findById(tripId));
    }

    @PostMapping
    public ResponseEntity<Object> create(
            @Valid @RequestBody TripRequest tripRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.create(tripRequest));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<Object> update(@PathVariable Long tripId, @Valid @RequestBody TripRequest tripRequest) {
        tripService.update(tripId, tripRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Object> delete(@PathVariable Long tripId) {
        tripService.delete(tripId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
