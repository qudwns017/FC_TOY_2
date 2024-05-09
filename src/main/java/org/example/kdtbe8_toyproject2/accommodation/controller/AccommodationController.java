package org.example.kdtbe8_toyproject2.accommodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
@RequestMapping("/api") //수정필요("api/accommodation/{trip_id}")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;;
    private final AccommodationMapper accommodationMapper;

    @PostMapping("/trips/{trip_id}/accommodation") //api/trips/accommodation/{trip_id}/{id}?
    public ResponseEntity<?> create(@PathVariable("trip_id") Long tripId, @Valid @RequestBody AccomodationRequest accomodationRequest) throws Exception {
        AccommodationDto accommodationEntity = accommodationService.create(tripId, accomodationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationEntity);
    }


    @DeleteMapping("/trips/{trip_id}/accommodation/{id}")
    public ResponseEntity<?> delete(@PathVariable("trip_id") Long tripId, @PathVariable Long id) throws Exception {
         //accommodationService.delete(tripId, id);
        int accommodationEntity = accommodationService.delete(tripId, id);
        if(accommodationEntity == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(accommodationEntity);
    }

    @GetMapping("/trips/{trip_id}/accommodation")
    public ResponseEntity<?> findByTripId(@PathVariable("trip_id") Long tripId) throws NotFoundException {
        List<AccommodationEntity> accommodationList = accommodationMapper.findByTripId(tripId);
        return new ResponseEntity<>(accommodationList, HttpStatus.OK);
    }
}

