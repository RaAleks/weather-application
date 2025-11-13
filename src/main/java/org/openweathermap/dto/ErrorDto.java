package org.openweathermap.dto;

import java.time.LocalDateTime;

public record ErrorDto(
        LocalDateTime timestamp,
        String message) {
}
