package org.example.kdtbe8_toyproject2.itinerary.db;

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
    private Long id;
    private Long tripId;
    private String name;
    private Integer type;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String comment;
}
