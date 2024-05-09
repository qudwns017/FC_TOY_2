package org.example.kdtbe8_toyproject2.itinerary.db;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StayEntity {

    private Long itineraryId;
    private String place;
}
