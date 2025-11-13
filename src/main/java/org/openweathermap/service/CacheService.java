package org.openweathermap.service;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.openweathermap.dto.CityWeatherInfo;
import org.openweathermap.dto.remote.WeatherResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CacheService {

    private final Cache<String, WeatherResponse> caffeineCache;


    public WeatherResponse getIfPresent(String city) {
        return caffeineCache.getIfPresent(city);
    }

    public void put(String city, WeatherResponse weatherResponse) {
        caffeineCache.put(city, weatherResponse);
    }
}
