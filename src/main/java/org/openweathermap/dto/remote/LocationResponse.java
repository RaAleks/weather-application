package org.openweathermap.dto.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationResponse(
        String name,
        @JsonProperty("local_names") Map<String, String> localNames,
        double lat,
        double lon,
        String country,
        String state
) {
}