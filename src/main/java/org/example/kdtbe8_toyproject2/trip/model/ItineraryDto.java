package org.example.kdtbe8_toyproject2.trip.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDto {
    private Long itineraryId;
    private String itineraryName;
    private Boolean type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String comment;
}
