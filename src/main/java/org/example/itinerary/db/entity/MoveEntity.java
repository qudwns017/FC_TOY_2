package org.example.itinerary.db.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MoveEntity {

    private Long itineraryId;
    private String transportation;
    private String departurePlace;
    private String arrivalPlace;
}
