package org.example.kdtbe8_toyproject2.global.error.errorcode;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ErrorCode;
import org.example.kdtbe8_toyproject2.global.error.exception.ItineraryException;
import org.example.kdtbe8_toyproject2.global.error.exception.TripException;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum TripError implements ErrorCode {
    TRIP_NOT_FOUND("여행정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

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
    public TripException defaultException() {
        return new TripException(this);
    }

    @Override
    public TripException defaultException(Throwable cause) {
        return new TripException(this, cause);
    }
}
