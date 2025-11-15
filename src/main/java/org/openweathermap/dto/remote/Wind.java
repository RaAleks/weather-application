package org.openweathermap.dto.remote;

/**
 * Represents wind information from the OpenWeather API.
 * <p>
 * Contains wind speed, direction, and gusts.
 * </p>
 *
 * @param speed Wind speed in meters per second
 * @param deg   Wind direction in degrees (meteorological)
 * @param gust  Wind gust speed in meters per second
 */
public record Wind(
        double speed,
        int deg,
        double gust
) {
}