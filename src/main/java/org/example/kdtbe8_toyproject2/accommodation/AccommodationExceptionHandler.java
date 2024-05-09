package org.example.kdtbe8_toyproject2.accommodation;

import lombok.extern.slf4j.Slf4j;
import org.example.kdtbe8_toyproject2.accommodation.controller.AccommodationController;
import org.example.kdtbe8_toyproject2.accommodation.service.AccommodationService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
@Slf4j
@RestControllerAdvice(basePackageClasses = {AccommodationController.class})
@Order(1)
public class AccommodationExceptionHandler {

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.error("SQLIntegrityConstraintViolationException", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Api> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: JSON parse error", e);

      var response = Api.builder()
              .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
              .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
              .build();

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body(response);
    }
}
