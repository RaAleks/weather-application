package org.openweathermap.dto;

public record WeatherResponseDto(
        WeatherDto weather,
        TemperatureDto temperature,
        int visibility,
        WindDto wind,
        long datetime,
        SysDto sys,
        int timezone,
        String name
) {
}
