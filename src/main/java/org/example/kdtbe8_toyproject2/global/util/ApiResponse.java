package org.example.kdtbe8_toyproject2.global.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ApiSimpleError;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ErrorCode;
import org.example.kdtbe8_toyproject2.global.error.exception.CustomException;

import java.time.Instant;
import java.util.List;

/**
 *
 * @param code 에러 코드 명
 * @param name 오류 이름
 * @param message 오류 메시지
 * @param cause
 * @param status 상태 코드 값
 * @param data 응답 값
 * @param timestamp 발생 시각
 */
@Builder
public record ApiResponse<T>(
        @JsonInclude(Include.NON_EMPTY) String code,
        Integer status,
        String name,
        @JsonInclude(Include.NON_EMPTY) String message,
        @JsonInclude(Include.NON_EMPTY) T data,
        @JsonInclude(Include.NON_EMPTY) List<ApiSimpleError> cause,
        Instant timestamp
) {
    public static ApiResponse of(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        String errorName = exception.getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ApiResponse.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(exception.getMessage())
                .cause(ApiSimpleError.listOfCauseSimpleError(exception.getCause()))
                .build();
    }

    public ApiResponse {
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