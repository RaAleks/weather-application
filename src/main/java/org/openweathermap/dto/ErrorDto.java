package org.openweathermap.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorDto(
        LocalDateTime timestamp,
        String message,
        Map<String, String> errors) {
}
