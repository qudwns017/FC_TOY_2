package org.example.kdtbe8_toyproject2.accommodation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.AssertTrue;
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

    private Long id;
    @NotNull(message= "여행 아이디를 입력해주세요.")
    private Long tripId;
    @NotBlank(message = "숙박 장소는 빈 칸일 수 없습니다.")
    private String name;
    @NotNull(message = "체크인 시간은 빈칸일 수 없습니다. ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkInDatetime;

    @NotNull(message = "체크아웃 시간은 빈칸 일 수 없습니다.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkOutDatetime;

    @AssertTrue(message = "체크아웃 시간은 체크인 시간보다 늦어야 합니다. ")
    public boolean isValidPeriod() {
        return checkOutDatetime.isAfter(checkInDatetime);
    }

}
