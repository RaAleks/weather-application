package org.openweathermap.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.openweathermap.dto.remote.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private static final String DEFAULT_API_KEY = "default";

    private final Cache<String, WeatherResponse> weatherCache;
    private final Cache<String, String> keyCache;

    public CacheService(Cache<String, WeatherResponse> weatherCache,
                        Cache<String, String> keyCache,
                        @Value("${openweather.api-key}") String defaultApiKey) {

        this.weatherCache = weatherCache;
        this.keyCache = keyCache;

        this.keyCache.put(DEFAULT_API_KEY, defaultApiKey);
    }


    public WeatherResponse getWeatherIfPresent(String city) {
        return weatherCache.getIfPresent(city);
    }

    public void putWeather(String city, WeatherResponse weatherResponse) {
        weatherCache.put(city, weatherResponse);
    }

    public String getKeyIfPresent(String username) {
        return keyCache.getIfPresent(username);
    }

    public String getDefaultApiKey() {
        return keyCache.getIfPresent(DEFAULT_API_KEY);
    }

    public void putKey(String username, String key) {
        keyCache.put(username, key);
    }

    public void removeKey(String username) {
        keyCache.invalidate(username);
    }
}
