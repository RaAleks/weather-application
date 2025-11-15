package org.openweathermap.service;

import org.openweathermap.dto.remote.WeatherResponse;

/**
 * Service interface for fetching weather data.
 * <p>
 * Provides methods to fetch weather information either by city name with an API key
 * or by using a username-associated API key.
 * </p>
 */
public interface WeatherService {

    /**
     * Fetches weather data for the given city using the provided API key.
     *
     * @param city   City name
     * @param apiKey API key for OpenWeather
     * @return {@link WeatherResponse} containing current weather data
     */
    WeatherResponse fetchWeather(String city,
                                 String apiKey);

    /**
     * Fetches weather data for the given city using the API key associated with a username.
     *
     * @param city     City name
     * @param username Username whose API key should be used
     * @return {@link WeatherResponse} containing current weather data
     */
    WeatherResponse fetchWithUsername(String city,
                                      String username);
}