package org.example.kdtbe8_toyproject2.global.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

/**
 *
 * @param code 에러 코드 명
 * @param name 오류 이름
 * @param message 오류 메시지
 * @param status 상태 코드 값
 * @param data 응답 값
 * @param timestamp 발생 시각
 */
@Builder
public record ApiResponseUtil<T>(
        @JsonInclude(Include.NON_EMPTY) String code,
        Integer status,
        String name,
        @JsonInclude(Include.NON_EMPTY) String message,
        @JsonInclude(Include.NON_EMPTY) T data,
        Instant timestamp
) {

    public ApiResponseUtil {
        if (status == null) {
            status = 500;
        }

        if (name == null) {
            name = "ApiError";
        }

        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }
}