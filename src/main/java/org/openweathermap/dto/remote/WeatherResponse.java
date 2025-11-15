package org.openweathermap.dto.remote;

import java.util.List;

/**
 * Represents the full weather response from the OpenWeather API.
 * <p>
 * Contains all relevant information about current weather conditions,
 * including coordinates, main metrics, wind, rain, clouds, system info,
 * and metadata about the location and API response.
 * </p>
 *
 * @param coord      Geographic coordinates of the location
 * @param weather    List of weather condition objects
 * @param base       Internal parameter from OpenWeather API
 * @param main       Main weather metrics (temperature, pressure, humidity, etc.)
 * @param visibility Visibility in meters
 * @param wind       Wind information
 * @param rain       Rain information (if available)
 * @param clouds     Cloud information
 * @param dt         Time of data calculation (Unix timestamp)
 * @param sys        System-related information (country, sunrise, sunset, etc.)
 * @param timezone   Shift in seconds from UTC
 * @param id         City ID
 * @param name       City name
 * @param cod        Internal parameter indicating response code
 */
public record WeatherResponse(
        Coord coord,
        List<Weather> weather,
        String base,
        Main main,
        int visibility,
        Wind wind,
        Rain rain,
        Clouds clouds,
        long dt,
        Sys sys,
        int timezone,
        long id,
        String name,
        int cod
) {
}