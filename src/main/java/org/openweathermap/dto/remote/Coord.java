package org.openweathermap.dto.remote;

/**
 * Represents geographic coordinates.
 *
 * @param lon the longitude of the location
 * @param lat the latitude of the location
 */
public record Coord(
        double lon,
        double lat
) {
}