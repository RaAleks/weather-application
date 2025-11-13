package org.openweathermap.dto;

public record WeatherDto(
        String main,
        String description
) {
}