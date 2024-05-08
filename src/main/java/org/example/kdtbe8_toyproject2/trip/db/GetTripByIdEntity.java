package org.example.kdtbe8_toyproject2.trip.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kdtbe8_toyproject2.accomodation.db.AccommodationEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetTripByIdEntity {
    private Long tripId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isOversea;
    private String comment;
    private List<AccommodationEntity> accommodation;
    private List<ItineraryEntity> itinerary;
}
