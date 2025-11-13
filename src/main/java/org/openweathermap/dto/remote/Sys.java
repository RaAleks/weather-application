package org.openweathermap.dto.remote;

public record Sys(
        int type,
        int id,
        String country,
        long sunrise,
        long sunset
) {
}