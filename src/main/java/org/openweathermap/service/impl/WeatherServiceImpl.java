package org.openweathermap.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.dto.remote.LocationResponse;
import org.openweathermap.dto.remote.WeatherResponse;
import org.openweathermap.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of {@link WeatherService} that provides weather data fetching logic.
 * <p>
 * Uses caching and the {@link GeoDecoderServiceImpl} to retrieve weather information.
 * Fetches weather data by city name or by username-associated API key.
 * </p>
 */
@RequiredArgsConstructor
@Service
@Slf4j
 class WeatherServiceImpl implements WeatherService {

    private final CacheServiceImpl cacheService;
    private final GeoDecoderServiceImpl geoDecoderService;

    /**
     * Fetches weather data for the given city.
     * <p>
     * If the weather data for the city is already cached, it returns the cached data.
     * Otherwise, it fetches the data from the OpenWeather API and caches it.
     * If the API key is null, the default API key is used.
     * </p>
     *
     * @param city   City name
     * @param apiKey API key for OpenWeather; if null, default API key is used
     * @return {@link WeatherResponse} containing current weather data
     */
    public WeatherResponse fetchWeather(String city,
                                        String apiKey) {

        WeatherResponse cachedCityWeatherInfo = cacheService.getWeatherIfPresent(city);

        if (Objects.nonNull(cachedCityWeatherInfo)) {
            return cachedCityWeatherInfo;
        }

        if (Objects.isNull(apiKey)) {
            log.warn("Api key is empty, using default");
            apiKey = cacheService.getDefaultApiKey();
        }

        List<LocationResponse> locationResponseArr = geoDecoderService.decodeCity(city, apiKey);
        LocationResponse locationResponse = locationResponseArr.get(0);

        WeatherResponse cityWeatherInfo = geoDecoderService.fetchWeather(
                locationResponse.lat(),
                locationResponse.lon(),
                apiKey
        );

        cacheService.putWeather(city, cityWeatherInfo);

        return cityWeatherInfo;
    }

    /**
     * Fetches weather data for the given city using the API key associated with a username.
     * <p>
     * Throws {@link IllegalArgumentException} if no API key is associated with the username.
     * </p>
     *
     * @param city     City name
     * @param username Username whose API key should be used
     * @return {@link WeatherResponse} containing current weather data
     */
    public WeatherResponse fetchWithUsername(String city,
                                             String username) {
        String apiKey = cacheService.getKeyIfPresent(username);

        if (Objects.isNull(apiKey)) {
            throw new IllegalArgumentException(String.format(
                    "Unable to fetch weather, no api key for username [%s]", username
            ));
        }

        return fetchWeather(city, apiKey);
    }

}
