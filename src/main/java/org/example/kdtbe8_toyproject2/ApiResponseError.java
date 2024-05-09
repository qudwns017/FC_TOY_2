package org.example.kdtbe8_toyproject2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

/**
 *
 * @param code 에러 코드 명
 * @param status 상태 코드 값
 * @param name 오류 이름
 * @param message 오류 메시지
 * @param timestamp 발생 시각
 */
@Builder
//@Setter
public record ApiResponseError(
        String code,
        Integer status,
        String name,
        String message,
        Instant timestamp
) {
    public static ApiResponseError of(RuntimeException exception) {
        String errorName = exception.getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ApiResponseError.builder()
                .name(errorName)
                .message(exception.getMessage())
                .build();
    }

    public ApiResponseError {
        if (code == null) {
            code = "API_ERROR";
        }

        if (status == null) {
            status = 500;
        }

        if (name == null) {
            name = "ApiError";
        }

        if (message == null || message.isBlank()) {
            message = "API 오류";
        }

        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }
}