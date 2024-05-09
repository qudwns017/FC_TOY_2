package org.example.kdtbe8_toyproject2.global.error.exception;


import org.example.kdtbe8_toyproject2.global.error.errorcode.ErrorCode;

public class TravelException extends CustomException {
     public TravelException() {
        super();
    }

    public TravelException(String message) {
        super(message);
    }

    public TravelException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelException (ErrorCode errorCode) {
        super(errorCode);
    }

    public TravelException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}