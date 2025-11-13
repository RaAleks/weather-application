package org.openweathermap.dto;

public record TemperatureDto(
        double temp,
        double feels_like
) {
}