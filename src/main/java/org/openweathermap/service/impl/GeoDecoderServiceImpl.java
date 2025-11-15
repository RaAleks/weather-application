package org.openweathermap.service.impl;

import lombok.RequiredArgsConstructor;
import org.openweathermap.dto.remote.LocationResponse;
import org.openweathermap.dto.remote.WeatherResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Service for interacting with the OpenWeather API to decode city names
 * into coordinates and fetch current weather data.
 * <p>
 * Uses a {@link RestClient} to make HTTP requests to the OpenWeather API.
 * </p>
 */
@RequiredArgsConstructor
@Service
class GeoDecoderServiceImpl {

    private final RestClient weatherRestClient;

    /**
     * Decodes a city name into geographic coordinates using the OpenWeather Geo API.
     *
     * @param city   The city name to decode
     * @param apiKey API key for OpenWeather
     * @return A list of {@link LocationResponse} containing location details
     */
    List<LocationResponse> decodeCity(String city, String apiKey) {
        String url = String.format("/geo/1.0/direct?q=%s&limit=1&appid=%s", city, apiKey);

        return weatherRestClient.get().uri(url).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    /**
     * Fetches current weather data for the specified coordinates.
     *
     * @param latitude  Latitude of the location
     * @param longitude Longitude of the location
     * @param apiKey    API key for OpenWeather
     * @return A {@link WeatherResponse} object containing current weather data
     */
     WeatherResponse fetchWeather(double latitude,
                                 double longitude,
                                 String apiKey) {

        String url = String.format("/data/2.5/weather?lat=%s&lon=%s&appid=%s", latitude, longitude, apiKey);

        return weatherRestClient.get().uri(url).retrieve().body(WeatherResponse.class);
    }
}
