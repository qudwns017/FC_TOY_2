package org.example.kdtbe8_toyproject2.trip.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.util.ApiResponseUtil;
import org.example.kdtbe8_toyproject2.trip.db.GetTripByIdEntity;
import org.example.kdtbe8_toyproject2.trip.db.TripListEntity;
import org.example.kdtbe8_toyproject2.trip.model.TripDto;
import org.example.kdtbe8_toyproject2.trip.model.TripRequest;
import org.example.kdtbe8_toyproject2.trip.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trips")
@Tag(name = "trips", description = "여행")
public class TripController {
    private final TripService tripService;

    @Operation(
            summary = "여행 리스트 조회",
            description = "등록된 여행들의 리스트를 조회합니다.",
            tags = { "trips" },
            responses = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
    }
    )
    @GetMapping
    public ApiResponseUtil<?> getTripList() {
        return ApiResponseUtil.<List<TripListEntity>>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.findAll())
                .build();
    }

    @Operation(
            summary = "특정 여행 조회",
            description = "TripId를 통해 하나의 여정을 조회합니다.",
            tags = { "trips" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @GetMapping("/{tripId}")
    public ApiResponseUtil<?> getTripById(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId
    ) {
        return ApiResponseUtil.<GetTripByIdEntity>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.findById(tripId))
                .build();
    }

    @Operation(
            summary = "여행 생성",
            description = "새로운 여행을 생성합니다.",
            tags = { "trips" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @PostMapping
    public ApiResponseUtil<?> create(
            @Parameter(name ="tripRequest", description = "Trip 정보 입력", required = true)
            @Valid @RequestBody TripRequest tripRequest
    ) {
        return ApiResponseUtil.<TripDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.create(tripRequest))
                .build();
    }

    @Operation(
            summary = "여행 수정",
            description = "등록된 여행 하나를 수정합니다.",
            tags = { "trips" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @PutMapping("/{tripId}")
    public ApiResponseUtil<?> update(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId,
            @Parameter(name ="tripRequest", description = "Trip 정보 입력", required = true)
            @Valid @RequestBody TripRequest tripRequest) {
        return ApiResponseUtil.<TripDto>builder()
                .status(HttpStatus.OK.value())
                .name(HttpStatus.OK.name())
                .data(tripService.update(tripId, tripRequest))
                .build();
    }

    @Operation(
            summary = "여행 삭제",
            description = "등록된 여행 하나를 삭제합니다.",
            tags = { "trips" },
            responses = {
                    @ApiResponse(responseCode = "202", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 TripId"),
            }
    )
    @DeleteMapping("/{tripId}")
    public ApiResponseUtil<?> delete(
            @Parameter(name ="tripId", description = "TripId 입력", required = true, in = ParameterIn.PATH)
            @PathVariable Long tripId
    ) {
        tripService.delete(tripId);
        return ApiResponseUtil.<TripDto>builder()
                .status(HttpStatus.ACCEPTED.value())
                .name(HttpStatus.ACCEPTED.name())
                .build();
    }
}
