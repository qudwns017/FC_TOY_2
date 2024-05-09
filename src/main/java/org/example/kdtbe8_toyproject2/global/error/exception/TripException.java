package org.example.kdtbe8_toyproject2.global.error.exception;


import org.example.kdtbe8_toyproject2.global.error.errorcode.ErrorCode;

public class TripException extends CustomException {
    // 귀찮으면 이런 하위 예외 타입 안 만들고, enum 에러 코드만 새로 생성해서 defaultException 작성 시 바로 new CustomException 써도 됨.
    // 근데 그래도 이거 별로 안 귀찮으니까 그냥 단축키 눌러서 생성자를 자동으로 만드는 건 어떨지. (인텔리제이: Ctrl + O, STS/Eclipse: Alt + Shift + S)
    public TripException() {
        super();
    }

    public TripException(String message) {
        super(message);
    }

    public TripException(String message, Throwable cause) {
        super(message, cause);
    }

    public TripException (ErrorCode errorCode) {
        super(errorCode);
    }

    public TripException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}