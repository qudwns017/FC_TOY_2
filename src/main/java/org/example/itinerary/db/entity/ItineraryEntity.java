package org.example.itinerary.db.entity;

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
    @Generated
    private Long itineraryId;
    private Long tripId;
    private String itineraryName;
    private Integer type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String content;
}
