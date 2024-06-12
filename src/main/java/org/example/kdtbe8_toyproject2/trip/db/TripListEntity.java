package org.example.kdtbe8_toyproject2.trip.db;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kdtbe8_toyproject2.itinerary.db.ItineraryNameEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripListEntity {
    private Long tripId;
    private String tripName;
    private List<ItineraryNameEntity> itineraries;
}
