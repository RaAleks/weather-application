package org.openweathermap.dto;

/**
 * Represents basic weather condition information.
 * <p>
 * Contains the main weather category and a descriptive text.
 * </p>
 *
 * @param main        Main weather category (e.g., Rain, Clear, Clouds)
 * @param description Detailed weather description
 */
public record WeatherDto(
        String main,
        String description
) {
}