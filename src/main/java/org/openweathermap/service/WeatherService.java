package org.openweathermap.service;

import lombok.RequiredArgsConstructor;
import org.openweathermap.dto.CityWeatherInfo;
import org.openweathermap.dto.remote.LocationResponse;
import org.openweathermap.dto.remote.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final CacheService cacheService;
    private final RestClient weatherRestClient;
    private final GeoDecoderService geoDecoderService;

    public WeatherResponse fetchWeather(String city,
                                        String apiKey) {
        WeatherResponse cachedCityWeatherInfo = cacheService.getIfPresent(city);

        if (Objects.nonNull(cachedCityWeatherInfo)) {
            return cachedCityWeatherInfo;
        }

        LocationResponse locationResponse = geoDecoderService.decodeCity(city, apiKey);

        WeatherResponse cityWeatherInfo = geoDecoderService.fetchWeather(locationResponse.lat(), locationResponse.lon(), apiKey);

        cacheService.put(city, cityWeatherInfo);

        return cityWeatherInfo;
    }

}
