package org.example.kdtbe8_toyproject2.trip.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripEntity {
    private Long tripId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isOversea;
}
