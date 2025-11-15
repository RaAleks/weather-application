package org.openweathermap.dto.remote;

/**
 * Represents weather condition information from the OpenWeather API.
 * <p>
 * Contains ID, main category, description, and icon code for the weather.
 * </p>
 *
 * @param id          Weather condition ID
 * @param main        Group of weather parameters (e.g., Rain, Snow, Clear)
 * @param description Weather condition description (localized)
 * @param icon        Weather icon code
 */
public record Weather(
        int id,
        String main,
        String description,
        String icon
) {
}