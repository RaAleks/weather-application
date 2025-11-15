package org.openweathermap.dto.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Represents a location returned by the OpenWeather API.
 * <p>
 * Includes information such as the name, country, state, coordinates,
 * and localized names for the location.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationResponse(
        /** Name of the location */
        String name,

        /** Localized names of the location, keyed by language code */
        @JsonProperty("local_names") Map<String, String> localNames,

        /** Latitude of the location */
        double lat,

        /** Longitude of the location */
        double lon,

        /** Country code of the location */
        String country,

        /** State or region of the location (if available) */
        String state
) {
}