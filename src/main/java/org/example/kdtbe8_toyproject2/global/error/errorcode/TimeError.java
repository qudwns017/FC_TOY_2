package org.example.kdtbe8_toyproject2.global.error.errorcode;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ErrorCode;
import org.example.kdtbe8_toyproject2.global.error.exception.ItineraryException;
import org.example.kdtbe8_toyproject2.global.error.exception.TimeException;
import org.example.kdtbe8_toyproject2.global.error.exception.TripException;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum TimeError implements ErrorCode {
    TIME_ERROR("시간이 올바르지 않습니다.", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public TimeException defaultException() {
        return new TimeException(this);
    }

    @Override
    public TimeException defaultException(Throwable cause) {
        return new TimeException(this, cause);
    }
}
