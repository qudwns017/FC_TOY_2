package org.example.kdtbe8_toyproject2.accommodation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.example.kdtbe8_toyproject2.accommodation.db.AccommodationEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationDto {
    private Long id;
    private Long tripId;
    private String name;
    private LocalDateTime checkInDatetime;
    private LocalDateTime checkOutDatetime;

    public static AccommodationDto toAccommodationDto(AccommodationEntity accommodationEntity){
        return AccommodationDto.builder()
                .tripId(accommodationEntity.getTripId())
                .id(accommodationEntity.getId())
                .name(accommodationEntity.getName())
                .checkInDatetime(accommodationEntity.getCheckInDatetime())
                .checkOutDatetime(accommodationEntity.getCheckOutDatetime())
                .build();
    }
}

