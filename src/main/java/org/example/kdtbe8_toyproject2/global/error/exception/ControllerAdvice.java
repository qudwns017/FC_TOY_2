package org.example.kdtbe8_toyproject2.global.error.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.global.error.errorcode.ApiResponseError;
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
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errorDefaultMessages = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        String objectName = e.getObjectName();
        e.getFieldErrors()
                .forEach(error -> errorDefaultMessages.put(error.getField(), error.getDefaultMessage()));

        log.error("failure check request validation, uri: {}, objectName: {}, {}", request.getRequestURI(), objectName, errors);
        return ResponseEntity.badRequest().body(errorDefaultMessages);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseError> handleMemberException(CustomException exception) {
        ApiResponseError response = ApiResponseError.of(exception);
        HttpStatus httpStatus = exception
                .getErrorCode()
                .defaultHttpStatus();

        return new ResponseEntity<>(response, httpStatus);
    }
}
