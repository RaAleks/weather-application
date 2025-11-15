package org.openweathermap.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.dto.remote.LocationResponse;
import org.openweathermap.dto.remote.WeatherResponse;
import org.openweathermap.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final CacheService cacheService;
    private final GeoDecoderService geoDecoderService;

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
        WeatherResponse cityWeatherInfo = geoDecoderService.fetchWeather(locationResponse.lat(), locationResponse.lon(), apiKey);

        cacheService.putWeather(city, cityWeatherInfo);


        return cityWeatherInfo;
    }

    public WeatherResponse fetchWithUsername(String city,
                                             String username) {
        String apiKey = cacheService.getKeyIfPresent(username);

        if (Objects.isNull(apiKey)) {
            throw new IllegalArgumentException(String.format("Unable to fetch weather, no api key for username [%s]", username));
        }

        return fetchWeather(city, apiKey);
    }

}
