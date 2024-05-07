package org.example.accomodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.accomodation.db.AccomodationEntity;
import org.example.accomodation.model.AccomodationDTO;
import org.example.accomodation.model.AccomodationRequest;
import org.example.accomodation.service.AccomodationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccomodationController {

    private final AccomodationService accomodationService;;

    @PostMapping("/trips/{tripId}/accommodation")
    public void create(@PathVariable Long tripId, @Valid @RequestBody AccomodationRequest accomodationRequest){
        accomodationService.create(tripId, accomodationRequest);
    }

    @DeleteMapping("/trips/{trip_id}/accommodation/{id}")
    public void delete(@PathVariable Long trip_id, @PathVariable Long id){
         accomodationService.delete(trip_id, id);
    }

  /*  @GetMapping("/trips/{trip_id}/accommodation/{id}") //findByTripId인데 accomId가 필요한가
    public List<AccomodationEntity> findByTripId(@PathVariable Long trip_id, @PathVariable Long id){
        return accomodationService.findByTripId(trip_id,id);
    }*/

    @GetMapping("/trips/{trip_id}/accommodation")
    public AccomodationDTO findByTripId(@PathVariable Long trip_id){
        var entity =  accomodationService.findByTripId(trip_id);
        return entity;
    }
}

