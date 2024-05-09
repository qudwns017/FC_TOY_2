package org.example.kdtbe8_toyproject2.itinerary.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryNameEntity {
    private Long itineraryId;
    private String itineraryName;
}
