package org.openweathermap.mapper;

import org.openweathermap.dto.*;
import org.openweathermap.dto.remote.WeatherResponse;

/**
 * Mapper class for converting {@link WeatherResponse} objects
 * from the OpenWeather API into {@link WeatherResponseDto} objects
 * used by the application.
 * <p>
 * This class handles the transformation of nested fields such as
 * weather details, temperature, wind, and system information.
 * </p>
 */
public class WeatherMapper {

    /**
     * Converts a {@link WeatherResponse} object into a {@link WeatherResponseDto}.
     * <p>
     * Maps the first element of the weather list to a {@link WeatherDto},
     * the main temperature data to {@link TemperatureDto},
     * wind information to {@link WindDto},
     * and system data to {@link SysDto}.
     * </p>
     *
     * @param response the {@link WeatherResponse} object from the API; may be {@code null}
     * @return the corresponding {@link WeatherResponseDto}, or {@code null} if input is {@code null}
     */
    public static WeatherResponseDto toDto(WeatherResponse response) {
        if (response == null) return null;

        return new WeatherResponseDto(
                new WeatherDto(
                        response.weather().get(0).main(),
                        response.weather().get(0).description()
                ),
                new TemperatureDto(
                        response.main().temp(),
                        response.main().feels_like()
                ),
                response.visibility(),
                new WindDto(
                        response.wind().speed()
                ),
                response.dt(),
                new SysDto(
                        response.sys().sunrise(),
                        response.sys().sunset()
                ),
                response.timezone(),
                response.name()
        );
    }
}
