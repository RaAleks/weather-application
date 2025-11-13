package org.openweathermap.exception;

import org.openweathermap.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handeException(Exception ex) {
        ErrorDto errorResponse = new ErrorDto(
                LocalDateTime.now(),
                "Error",
                null);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
