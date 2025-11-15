package org.openweathermap.dto.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents rain information from the OpenWeather API.
 * <p>
 * Contains the volume of rain for the last 1 hour.
 * </p>
 *
 * @param hourly Rain volume for the last hour (mm)
 */
public record Rain(
        @JsonProperty("_1h")
        double hourly
) {
}