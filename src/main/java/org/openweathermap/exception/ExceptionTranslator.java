package org.openweathermap.exception;

import org.openweathermap.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for the application.
 * <p>
 * Translates exceptions into standardized error responses with HTTP status codes.
 * </p>
 */
@ControllerAdvice
public class ExceptionTranslator {

    /**
     * Handles {@link MethodArgumentNotValidException} and returns a standardized error response.
     *
     * @param ex The exception to handle
     * @return A {@link ResponseEntity} containing an {@link ErrorDto} with the error details and HTTP status 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handeException(Exception ex) {
        ErrorDto errorResponse = new ErrorDto(
                LocalDateTime.now(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
