package org.example.kdtbe8_toyproject2.trip.db;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripListEntity {
    private Long tripId;
    private String name;
}
