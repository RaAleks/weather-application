package org.openweathermap.dto.remote;

public record Wind(
            double speed,
            int deg,
            double gust
    ) {}