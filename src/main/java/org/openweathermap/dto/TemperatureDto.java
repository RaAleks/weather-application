package org.openweathermap.dto;

/**
 * Represents temperature information for a location.
 * <p>
 * Contains the current temperature and the human-perceived "feels like" temperature.
 * </p>
 *
 * @param temp       Current temperature
 * @param feels_like Human-perceived temperature
 */
public record TemperatureDto(
        double temp,
        double feels_like
) {
}