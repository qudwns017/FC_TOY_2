package org.example.kdtbe8_toyproject2.itinerary.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.kdtbe8_toyproject2.global.validation.Conditional;
import org.example.kdtbe8_toyproject2.itinerary.enums.ItineraryType;

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
    @NotBlank(message= "여정 이름을 입력해주세요.")
    private String itineraryName;
    @NotNull(message= "여정 타입을 입력해주세요.")
    private ItineraryType type;
    @NotNull(message= "시작시간은 빈칸일 수 없습니다.")
    private LocalDateTime startDatetime;
    @NotNull(message= "도착시간은 빈칸일 수 없습니다.")
    private LocalDateTime endDatetime;
    private String transportation;
    private String departurePlace;
    private String arrivalPlace;
    private String place;
    private String comment;
}
