package org.example.kdtbe8_toyproject2.global.error.errorcode;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ErrorCode;
import org.example.kdtbe8_toyproject2.global.error.exception.ItineraryException;
import org.example.kdtbe8_toyproject2.global.error.exception.TravelException;
import org.example.kdtbe8_toyproject2.global.error.exception.TripException;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum TravelError implements ErrorCode {
    TRIP_NOT_FOUND("여행정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ITINERARY_NOT_EXIST("해당 여정정보가 없습니다.", HttpStatus.NOT_FOUND),
    UPDATE_FAILED("업데이트 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
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

    @Override
    public TravelException defaultException() {
        return new TravelException(this);
    }

    @Override
    public TravelException defaultException(Throwable cause) {
        return new TravelException(this, cause);
    }
}
