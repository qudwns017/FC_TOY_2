package org.example.kdtbe8_toyproject2.itinerary.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItineraryEntity {

    @Id
    private Long itineraryId;
    private Long tripId;
    private String itineraryName;
    private Integer type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String comment;
}
