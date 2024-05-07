package org.example.kdtbe8_toyproject2.trip.controller;

import java.util.List;
import org.example.kdtbe8_toyproject2.trip.db.TripMapper;
import org.example.kdtbe8_toyproject2.trip.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.trip.model.TripAccommodationDto;
import org.example.kdtbe8_toyproject2.trip.model.TripDto;
import org.example.kdtbe8_toyproject2.trip.model.TripListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TripController {
    @Autowired
    private TripMapper mapper;

    @GetMapping("/trips")
    public ResponseEntity<?> getTripList() {
        List<TripListDto> list = mapper.getTripList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<?> getTripById(@PathVariable int id) {
        TripAccommodationDto trip = mapper.getTripById(id);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }
}
