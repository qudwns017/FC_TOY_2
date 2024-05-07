package org.example.accomodation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)

public class AccomodationDTO {
    private Long accomodationId;
    private Long tripId;
    private String name;
    private LocalDateTime checkInDatetime;
    private LocalDateTime checkOutDatetime;
}
