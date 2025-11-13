package org.openweathermap.dto.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain(
        @JsonProperty("_1h")
        double hourly
) {
}