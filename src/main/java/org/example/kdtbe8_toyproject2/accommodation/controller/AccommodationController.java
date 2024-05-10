package org.example.kdtbe8_toyproject2.accommodation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationMapper;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccomodationRequest;
import org.example.kdtbe8_toyproject2.accommodation.service.AccommodationService;
import org.example.kdtbe8_toyproject2.global.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips/{tripId}/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    @GetMapping("")
    public ApiResponse<?> findByTripId(@PathVariable Long tripId){
        //return new ResponseEntity<>(accommodationDtoList, HttpStatus.OK);
        return ApiResponse.<List<AccommodationDto>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.findByTripId(tripId))
                .build();
    }
    @PostMapping("")
    public ApiResponse<?> create(@PathVariable Long tripId, @Valid @RequestBody AccomodationRequest accomodationRequest){
        //return ResponseEntity.status(HttpStatus.CREATED).body(accommodationEntity);
        return ApiResponse.<AccommodationDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.create(tripId, accomodationRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long tripId,@PathVariable Long id){
        accommodationService.delete(tripId, id);
        return ApiResponse.builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();}


}