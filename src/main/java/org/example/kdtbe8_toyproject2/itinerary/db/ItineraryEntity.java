package org.example.kdtbe8_toyproject2.itinerary.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ItineraryEntity {
    private Long id;
    private Long tripId;
    private String name;
    private Integer type;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String comment;
}
