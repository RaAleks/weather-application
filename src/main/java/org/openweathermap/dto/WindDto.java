package org.openweathermap.dto;

/**
 * Represents wind information in the weather response DTO.
 * <p>
 * Contains only the wind speed in meters per second.
 * </p>
 *
 * @param speed Wind speed in meters per second
 */
public record WindDto(
        double speed
) {
}