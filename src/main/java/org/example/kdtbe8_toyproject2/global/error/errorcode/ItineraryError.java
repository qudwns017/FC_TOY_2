package org.example.kdtbe8_toyproject2.global.error.errorcode;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.global.error.exception.ItineraryException;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ItineraryError implements ErrorCode {
    USERNAME_ALREADY_EXISTS("해당 여행정보가 없습니다.", HttpStatus.NOT_FOUND);

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
    public ItineraryException defaultException() {
        return new ItineraryException(this);
    }

    @Override
    public ItineraryException defaultException(Throwable cause) {
        return new ItineraryException(this, cause);
    }
}
