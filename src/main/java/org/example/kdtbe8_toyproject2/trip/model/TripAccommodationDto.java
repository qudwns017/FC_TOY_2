package org.example.kdtbe8_toyproject2.trip.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripAccommodationDto {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isOversea;
    private String comment;
    private List<AccommodationDto> accommodation;
    private List<ItineraryDto> itinerary;
}
