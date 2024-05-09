package org.example.kdtbe8_toyproject2.global.error.errorcode;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.exception.ItineraryException;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ItineraryError implements ErrorCode {
    TRIP_NOT_EXIST("해당 여행정보가 없습니다.", HttpStatus.NOT_FOUND),
    ITINERARY_NOT_EXIST("해당 여정정보가 없습니다.", HttpStatus.NOT_FOUND),
    UPDATE_FAILED("업데이트 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

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
    public ItineraryException defaultException() {
        return new ItineraryException(this);
    }

    @Override
    public ItineraryException defaultException(Throwable cause) {
        return new ItineraryException(this, cause);
    }
}
