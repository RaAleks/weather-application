package org.openweathermap.dto.remote;

public record Weather(
            int id,
            String main,
            String description,
            String icon
    ) {}