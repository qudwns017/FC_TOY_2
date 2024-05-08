package org.example.kdtbe8_toyproject2.trip.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationEntity {
    private Long accommodationId;
    String accommodationName;
    LocalDateTime checkInDateTime;
    LocalDateTime checkOutDateTime;
}