package org.example.kdtbe8_toyproject2.itinerary.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ItineraryDto {

    private Long id;
    private Long tripId;
    private String name;
    private Integer type;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String comment;
    private String transportation;
    private String departurePlace;
    private String arrivalPlace;
    private String place;
}
