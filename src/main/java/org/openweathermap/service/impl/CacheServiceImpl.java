package org.openweathermap.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import org.openweathermap.dto.remote.WeatherResponse;
import org.openweathermap.service.CacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for managing cached weather data and API keys.
 * <p>
 * Provides methods to store and retrieve weather responses and API keys
 * using Caffeine caches.
 * </p>
 */
@Service
class CacheServiceImpl implements CacheService {

    private static final String DEFAULT_API_KEY = "default";

    private final Cache<String, WeatherResponse> weatherCache;
    private final Cache<String, String> keyCache;

    /**
     * Constructor for CacheService.
     *
     * @param weatherCache  Cache for storing weather responses
     * @param keyCache      Cache for storing API keys
     * @param defaultApiKey Default API key for OpenWeather
     */
    public CacheServiceImpl(Cache<String, WeatherResponse> weatherCache,
                            Cache<String, String> keyCache,
                            @Value("${openweather.api-key}") String defaultApiKey) {

        this.weatherCache = weatherCache;
        this.keyCache = keyCache;

        this.keyCache.put(DEFAULT_API_KEY, defaultApiKey);
    }

    /**
     * Retrieves cached weather data for a given city if present.
     *
     * @param city City name
     * @return Cached {@link WeatherResponse} or null if not present
     */
    public WeatherResponse getWeatherIfPresent(String city) {
        return weatherCache.getIfPresent(city);
    }

    /**
     * Stores weather data for a given city in the cache.
     *
     * @param city            City name
     * @param weatherResponse Weather data to store
     */
    public void putWeather(String city, WeatherResponse weatherResponse) {
        weatherCache.put(city, weatherResponse);
    }

    /**
     * Retrieves a cached API key for a given username if present.
     *
     * @param username Username associated with the API key
     * @return Cached API key or null if not present
     */
    public String getKeyIfPresent(String username) {
        return keyCache.getIfPresent(username);
    }

    /**
     * Retrieves the default API key from the cache.
     *
     * @return Default API key or null if not present
     */
    public String getDefaultApiKey() {
        return keyCache.getIfPresent(DEFAULT_API_KEY);
    }

    /**
     * Stores an API key in the cache for a given username.
     *
     * @param username Username to associate with the API key
     * @param key      API key to store
     */
    public void putKey(String username, String key) {
        keyCache.put(username, key);
    }

    /**
     * Removes an API key from the cache for a given username.
     *
     * @param username Username whose key should be removed
     */
    public void removeKey(String username) {
        keyCache.invalidate(username);
    }
}
