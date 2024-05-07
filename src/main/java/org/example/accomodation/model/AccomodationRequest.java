package org.example.accomodation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccomodationRequest {

    @NotNull
    private Long tripId;
    @NotBlank
    private String name;
    @NotNull
    private LocalDateTime checkInDatetime;
    @NotNull
    private LocalDateTime checkOutDatetime;
}
