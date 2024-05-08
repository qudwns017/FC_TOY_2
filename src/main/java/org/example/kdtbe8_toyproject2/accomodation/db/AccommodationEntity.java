package org.example.kdtbe8_toyproject2.accomodation.db;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccommodationEntity {
    private Long id; //private TripEntity trip;
    private Long tripId; // fk
    private String name;
    private LocalDateTime checkInDatetime;
    private LocalDateTime checkOutDatetime;
}
