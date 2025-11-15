package org.openweathermap.dto;

/**
 * Represents system-related information for a location.
 * <p>
 * Contains sunrise and sunset times in Unix timestamp format.
 * </p>
 *
 * @param sunrise Sunrise time in seconds since Unix epoch
 * @param sunset  Sunset time in seconds since Unix epoch
 */
public record SysDto(
        long sunrise,
        long sunset
) {
}