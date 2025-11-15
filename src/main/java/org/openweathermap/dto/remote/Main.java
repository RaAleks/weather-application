package org.openweathermap.dto.remote;

/**
 * Represents main weather information from the OpenWeather API.
 * <p>
 * Includes temperature, pressure, humidity, and other related metrics.
 * </p>
 *
 * @param temp       Current temperature
 * @param feels_like Human perception of the temperature
 * @param temp_min   Minimum observed temperature
 * @param temp_max   Maximum observed temperature
 * @param pressure   Atmospheric pressure (hPa)
 * @param humidity   Humidity percentage
 * @param sea_level  Atmospheric pressure at sea level (hPa)
 * @param grnd_level Atmospheric pressure at ground level (hPa)
 */
public record Main(
        double temp,
        double feels_like,
        double temp_min,
        double temp_max,
        int pressure,
        int humidity,
        int sea_level,
        int grnd_level
) {
}