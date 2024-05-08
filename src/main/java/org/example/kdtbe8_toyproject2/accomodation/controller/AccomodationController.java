package org.example.kdtbe8_toyproject2.accomodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
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

    @PostMapping("/trips/{tripId}/accommodation")
    public void create(@PathVariable Long tripId, @Valid @RequestBody AccomodationRequest accomodationRequest) throws Exception {
        accomodationService.create(tripId, accomodationRequest);
    }

    @DeleteMapping("/trips/{tripId}/accommodation/{id}")
    public void delete(@PathVariable Long tripId, @PathVariable Long id) throws Exception {
         accomodationService.delete(tripId, id);
    }

    @GetMapping("/trips/{tripId}/accommodation")
    public List<AccomodationDTO> findByTripId(@PathVariable Long tripId) throws NotFoundException {
        return accomodationService.findByTripId(tripId);

    }
}

 /*  @GetMapping("/trips/{trip_id}/accommodation/{id}") //findByTripId인데 accomId가 필요한가
    public List<AccomodationEntity> findByTripId(@PathVariable Long trip_id, @PathVariable Long id){
        return accomodationService.findByTripId(trip_id,id);
    }*/