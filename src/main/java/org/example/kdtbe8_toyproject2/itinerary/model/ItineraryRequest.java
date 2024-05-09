package org.example.kdtbe8_toyproject2.itinerary.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.kdtbe8_toyproject2.global.validation.Conditional;
import org.example.kdtbe8_toyproject2.itinerary.enums.itineraryType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Conditional.List(
        {
                @Conditional(
                        selected = "type",
                        values = "MOVE",
                        required = {"transportation", "departurePlace", "arrivalPlace"}),
                @Conditional(
                        selected = "type",
                        values = "STAY",
                        required = {"place"})
        }
)
public class ItineraryRequest {
    @NotBlank
    private String itineraryName;
    @NotNull
    private itineraryType type;
    @NotNull
    private LocalDateTime startDatetime;
    @NotNull
    private LocalDateTime endDatetime;
    private String transportation;
    private String departurePlace;
    private String arrivalPlace;
    private String place;
    private String comment;
}
