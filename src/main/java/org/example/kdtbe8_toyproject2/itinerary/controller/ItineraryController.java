package org.example.kdtbe8_toyproject2.itinerary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.util.ApiResponseUtil;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryDto;
import org.example.kdtbe8_toyproject2.itinerary.model.ItineraryRequest;
import org.example.kdtbe8_toyproject2.itinerary.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
@Tag(name = "itinerary", description = "여정")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @Operation(
            summary = "여정 조회",
            description = "특정 여행 ID에 속한 여정을 조회합니다.",
            tags = { "itinerary" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @GetMapping
    public ApiResponseUtil<?> findByTripId(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId
    ) {
        return ApiResponseUtil.<List<ItineraryDto>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(itineraryService.findByTripId(tripId))
                .build();
    }

    @Operation(
            summary = "여정 생성",
            description = "새로운 여정을 생성합니다. (시간 2024-05-10T10:10:10 형태로 입력)",
            tags = { "itinerary" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @PostMapping
    public ApiResponseUtil<?> create(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId,
            @Parameter(name ="itineraryRequest", description = "여정 정보 입력", required = true)
            @Validated @RequestBody ItineraryRequest itineraryRequest
    ){
        return ApiResponseUtil.<ItineraryDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(itineraryService.create(itineraryRequest,tripId))
                .build();
    }

    @Operation(
            summary = "여정 수정",
            description = "등록된 여정 하나를 수정합니다. (시간 2024-05-10T10:10:10 형태로 입력)",
            tags = { "itinerary" },
            responses = {
                    @ApiResponse(responseCode = "202", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId 또는 ItineraryId"),
            }
    )
    @PutMapping("/{itineraryId}")
    public ApiResponseUtil<?> update(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId,
            @Parameter(name ="itineraryId", description = "Itinerary 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long itineraryId,
            @Parameter(name ="itineraryRequest", description = "여정 정보 입력", required = true)
            @Validated @RequestBody ItineraryRequest itineraryRequest
    ) {
        return ApiResponseUtil.<ItineraryDto>builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .data(itineraryService.update(tripId, itineraryId, itineraryRequest))
                .build();
    }

    @Operation(
            summary = "여정 삭제",
            description = "특정 TripId에 속한 여정 하나를 삭제합니다.",
            tags = { "itinerary" },
            responses = {
                    @ApiResponse(responseCode = "202", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId 또는 ItineraryId"),
            }
    )
    @DeleteMapping("/{itineraryId}")
    public ApiResponseUtil<?> delete(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId,
            @Parameter(name ="itineraryId", description = "Itinerary 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long itineraryId
    ) {
        itineraryService.delete(tripId, itineraryId);

        return ApiResponseUtil.builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}
