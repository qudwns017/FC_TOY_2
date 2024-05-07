package org.example.kdtbe8_toyproject2.trip.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationDto {
    private Long accommodationId;
    String accommodationName;
    LocalDateTime checkInDateTime;
    LocalDateTime checkOutDateTime;
}