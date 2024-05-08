package org.example.kdtbe8_toyproject2.accomodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accomodation.db.AccomodationEntity;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationDTO;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationRequest;
import org.example.kdtbe8_toyproject2.accomodation.service.AccomodationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccomodationController {

    private final AccomodationService accomodationService;;

    @PostMapping("/trips/{trip_id}/accommodation")
    public void create(@PathVariable("trip_id") Long tripId, @Valid @RequestBody AccomodationRequest accomodationRequest) throws Exception {
         accomodationService.create(tripId, accomodationRequest);
    }

    @DeleteMapping("/trips/{trip_id}/accommodation/{id}")
    public void delete(@PathVariable("trip_id") Long tripId, @PathVariable Long id) throws Exception {
         accomodationService.delete(tripId, id);
    }

    @GetMapping("/trips/{trip_id}/accommodation")
    public List<AccomodationDTO> findByTripId(@PathVariable("trip_id") Long tripId) throws NotFoundException {
        return accomodationService.findByTripId(tripId);

    }
}

