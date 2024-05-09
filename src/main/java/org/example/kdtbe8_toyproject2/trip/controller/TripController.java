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

@Slf4j
@RequestMapping("/api/trips")
@RequiredArgsConstructor

@RestController
public class TripController {
    @Autowired
    private final TripService tripService;

    @GetMapping("")
    public ResponseEntity<?> getTripList() {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.findAll());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<?> getTripById(@PathVariable Long tripId) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.findById(tripId));
    }

    @PostMapping("")
    public ResponseEntity<Object> create(
            @Valid @RequestBody TripRequest tripRequest) {
        var entity = tripService.create(tripRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<Object> update(@PathVariable Long tripId, @Valid @RequestBody TripRequest tripRequest) {
        int result = tripService.update(tripId, tripRequest);

        if (result == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Object> delete(@PathVariable Long tripId) {
        int result = tripService.delete(tripId);

        // TODO : 삭제를 하게 되면 이와 관련된 다른 테이블 데이터도 같이 삭제되게 해야
        if (result == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
