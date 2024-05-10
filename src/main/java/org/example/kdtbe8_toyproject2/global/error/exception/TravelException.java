package org.example.kdtbe8_toyproject2.global.error.exception;


import lombok.Getter;
import org.example.kdtbe8_toyproject2.global.error.errorcode.TravelError;


@Getter
public class TravelException extends RuntimeException {
    protected TravelError errorCode;

    public TravelException(String message) {
        super(message);
    }

    public TravelException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelException (TravelError errorCode) {
        super(errorCode.defaultMessage());
        this.errorCode = errorCode;
    }

    public TravelException(TravelError errorCode, Throwable cause) {
        super(errorCode.defaultMessage(),cause);
        this.errorCode = errorCode;
    }
}