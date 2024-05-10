package org.example.kdtbe8_toyproject2.global.error.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.global.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseUtil<?> handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errorDefaultMessages = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        String objectName = e.getObjectName();
        e.getFieldErrors()
                .forEach(error -> errorDefaultMessages.put(error.getField(), error.getDefaultMessage()));

        log.error("failure check request validation, uri: {}, objectName: {}, {}", request.getRequestURI(), objectName, errors);
        return ApiResponseUtil.<Map<String,String>>builder()
                .code("FAILURE_CHECK_REQUEST")
                .status(HttpStatus.BAD_REQUEST.value())
                .name(objectName)
                .data(errorDefaultMessages)
                .build();
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseUtil> handleMemberException(CustomException exception) {
        ApiResponseUtil response = ApiResponseUtil.of(exception);
        HttpStatus httpStatus = exception
                .getErrorCode()
                .defaultHttpStatus();

        return new ResponseEntity<>(response, httpStatus);
    }
}
