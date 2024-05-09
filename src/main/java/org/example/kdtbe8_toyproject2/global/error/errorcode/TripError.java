package org.example.kdtbe8_toyproject2.global.error.errorcode;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.exception.AccommodationException;
import org.example.kdtbe8_toyproject2.global.error.exception.ItineraryException;
import org.example.kdtbe8_toyproject2.global.error.exception.TripException;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum TripError implements ErrorCode {
    TRIP_NOT_EXIST("해당 여행정보가 없습니다.", HttpStatus.NOT_FOUND),
    ACCOMMODATION_NOT_EXIST("해당 숙박정보가 없습니다.", HttpStatus.NOT_FOUND);

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

    // 부모 메서드보다 더 구체적인 타입으로 반환할 수 있다.
    @Override
    public TripException defaultException() {
        return new TripException(this);
    }

    @Override
    public TripException defaultException(Throwable cause) {
        return new TripException(this, cause);
    }
}
