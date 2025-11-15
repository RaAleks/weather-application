package org.openweathermap.dto.remote;

/**
 * Represents cloud information from the weather API.
 *
 * @param all the cloudiness percentage (0-100)
 */
public record Clouds(
        int all
) {
}