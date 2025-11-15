package org.openweathermap.dto.remote;

/**
 * Represents system-related information from the OpenWeather API.
 * <p>
 * Includes data such as country code, sunrise and sunset times,
 * and internal identifiers.
 * </p>
 *
 * @param type    Internal parameter indicating system type
 * @param id      Internal system ID
 * @param country Country code (ISO 3166-1 alpha-2)
 * @param sunrise Sunrise time in Unix timestamp (seconds since epoch)
 * @param sunset  Sunset time in Unix timestamp (seconds since epoch)
 */
public record Sys(
        int type,
        int id,
        String country,
        long sunrise,
        long sunset
) {
}