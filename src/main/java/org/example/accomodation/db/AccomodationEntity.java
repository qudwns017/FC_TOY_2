package org.example.accomodation.db;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccomodationEntity {

    @Id
    private Long accomodationId;
    private Long tripId; // fk
    private String name;
    private LocalDateTime checkInDatetime;
    private LocalDateTime checkOutDatetime;
}
