package org.openweathermap.dto;

import java.time.LocalDateTime;

/**
 * Represents a standard error response.
 * <p>
 * Contains the timestamp of the error and a descriptive message.
 * </p>
 *
 * @param timestamp The time when the error occurred
 * @param message   The error message
 */
public record ErrorDto(
        LocalDateTime timestamp,
        String message
) {
}