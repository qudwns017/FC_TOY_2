package org.example.kdtbe8_toyproject2.trip.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TripRequest {
    private Long tripId;
    @NotBlank(message = "제목은 빈 값일 수 없습니다.")
    @Size(min = 1, max = 50, message = "제목은 1 ~ 50자 사이여야 합니다.")
    private String tripName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @NotNull(message = "시작일자는 빈 값일 수 없습니다.")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @NotNull(message = "종료일자는 빈 값일 수 없습니다.")
    private LocalDate endDate;

    @NotNull(message = "국/내외 여부 항목은 빈 값일 수 없습니다.")
    private int isOversea;

    private String comment;

    @AssertTrue(message = "종료일은 시작일을 앞설 수 없습니다.")
    public boolean isValidPeriod() {
        return endDate.isAfter(startDate) || startDate.isEqual(endDate);
    }
}