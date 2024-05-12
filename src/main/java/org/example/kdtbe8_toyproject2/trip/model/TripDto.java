package org.example.kdtbe8_toyproject2.trip.model;

import lombok.*;
import org.example.kdtbe8_toyproject2.trip.db.TripEntity;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDto {
    private Long tripId;
    private String tripName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int isOversea;
    private String comment;

    public static TripDto toDto(TripEntity tripEntity) {
        return TripDto.builder()
                .tripId(tripEntity.getTripId())
                .tripName(tripEntity.getTripName())
                .startDate(tripEntity.getStartDate())
                .endDate(tripEntity.getEndDate())
                .isOversea(tripEntity.getIsOversea())
                .comment(tripEntity.getComment())
                .build();
    }
}
