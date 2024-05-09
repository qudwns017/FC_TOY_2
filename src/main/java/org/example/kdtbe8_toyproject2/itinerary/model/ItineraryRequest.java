package org.example.kdtbe8_toyproject2.itinerary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 50, message = "제목은 1 ~ 50자 사이여야 합니다.")
    private String itineraryName;

    @NotNull(message= "여정 타입을 입력해주세요.")
    private ItineraryType type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull(message = "시작일자는 빈 값일 수 없습니다.")
    private LocalDateTime startDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull(message= "도착시간은 빈칸일 수 없습니다.")
    private LocalDateTime endDatetime;


    private String transportation;
    private String departurePlace;
    private String arrivalPlace;
    private String place;
    private String comment;

    @AssertTrue(message = "종료시간은 시각시간을 앞설 수 없습니다.")
    public boolean isValidPeriod() {
        return endDatetime.isAfter(startDatetime);
    }
}
