package org.example.kdtbe8_toyproject2.trip.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class TripEntity {
    private Long tripId;
    private String tripName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int isOversea;
    private String comment;
}
