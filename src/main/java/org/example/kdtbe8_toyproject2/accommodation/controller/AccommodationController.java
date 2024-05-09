package org.example.kdtbe8_toyproject2.accommodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccomodationRequest;
import org.example.kdtbe8_toyproject2.accommodation.service.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<?> findByTripId(
            @PathVariable Long tripId
    ){
        List<AccommodationDto> accommodationDtoList = accommodationService.findByTripId(tripId);
        return new ResponseEntity<>(accommodationDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @PathVariable Long tripId,
            @Valid
            @RequestBody AccomodationRequest accomodationRequest
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationService.create(tripId, accomodationRequest));
    }

    @DeleteMapping("/{accommodationId}")
    public ResponseEntity<?> delete(@PathVariable Long accommodationId){
        accommodationService.delete(accommodationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}