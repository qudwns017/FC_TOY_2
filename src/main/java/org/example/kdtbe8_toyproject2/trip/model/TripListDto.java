package org.example.kdtbe8_toyproject2.trip.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripListDto {
    private Long tripId;
    private String name;
}
