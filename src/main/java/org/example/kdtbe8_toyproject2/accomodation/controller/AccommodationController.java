package org.example.kdtbe8_toyproject2.accomodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accomodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationRequest;
import org.example.kdtbe8_toyproject2.accomodation.service.AccommodationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;;

    @PostMapping("/trips/{trip_id}/accommodation")
    public void create(@PathVariable("trip_id") Long tripId, @Valid @RequestBody AccomodationRequest accomodationRequest) throws Exception {
         accommodationService.create(tripId, accomodationRequest);
    }

    @DeleteMapping("/trips/{trip_id}/accommodation/{id}")
    public void delete(@PathVariable("trip_id") Long tripId, @PathVariable Long id) throws Exception {
         accommodationService.delete(tripId, id);
    }

    @GetMapping("/trips/{trip_id}/accommodation")
    public List<AccommodationDto> findByTripId(@PathVariable("trip_id") Long tripId) throws NotFoundException {
        return accommodationService.findByTripId(tripId);

    }
}

