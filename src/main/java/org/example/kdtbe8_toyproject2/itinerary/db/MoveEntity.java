package org.example.kdtbe8_toyproject2.itinerary.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MoveEntity {
    private Long itineraryId;
    private String transportation;
    private String departurePlace;
    private String arrivalPlace;
}
