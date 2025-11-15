package org.openweathermap.dto;

/**
 * Represents the full weather response DTO.
 * <p>
 * Contains weather conditions, temperature, wind, system info, visibility,
 * timezone offset, and the location name.
 * </p>
 *
 * @param weather     Basic weather condition information
 * @param temperature Temperature information
 * @param visibility  Visibility in meters
 * @param wind        Wind information
 * @param datetime    Time of data calculation (Unix timestamp)
 * @param sys         System-related information (sunrise, sunset)
 * @param timezone    Shift in seconds from UTC
 * @param name        Location name
 */
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