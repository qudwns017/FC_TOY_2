package org.example.kdtbe8_toyproject2.accommodation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationDto;
import org.example.kdtbe8_toyproject2.accommodation.model.AccommodationRequest;
import org.example.kdtbe8_toyproject2.accommodation.service.AccommodationService;
import org.example.kdtbe8_toyproject2.global.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trips/{tripId}/accommodation")
@Tag(name = "accommodation", description = "숙박")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Operation(
            summary = "특정 숙박 조회",
            description = "특정 TripId에 속한 숙박을 조회합니다.",
            tags = { "accommodation" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @GetMapping("")
    public ApiResponseUtil<?> findByTripId(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId
    ){
        return ApiResponseUtil.<List<AccommodationDto>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.findByTripId(tripId))
                .build();
    }

    @Operation(
            summary = "숙박 생성",
            description = "특정 TripId에 속한 숙박을 생성합니다. (시간 2024-05-10 10:10 형태로 입력)",
            tags = { "accommodation" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @PostMapping("")
    public ApiResponseUtil<?> create(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId,
            @Parameter(name ="accommodationRequest", description = "숙박 정보 입력", required = true)
            @Valid @RequestBody AccommodationRequest accommodationRequest){
        return ApiResponseUtil.<AccommodationDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(accommodationService.create(tripId, accommodationRequest))
                .build();
    }

    @Operation(
            summary = "숙박 삭제",
            description = "특정 TripId에 속한 숙박을 삭제합니다. ",
            tags = { "accommodation" },
            responses = {
                    @ApiResponse(responseCode = "202", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId 또는 ItineraryId"),
            }
    )
    @DeleteMapping("/{accommodationId}")
    public ApiResponseUtil<?> delete(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId,
            @Parameter(name ="accommodationId", description = "AccommodationId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long accommodationId){
        accommodationService.delete(tripId, accommodationId);
        return ApiResponseUtil.builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}