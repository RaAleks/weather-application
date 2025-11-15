package org.openweathermap.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openweathermap.dto.remote.WeatherResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CacheServiceImplTest {

    private Cache<String, WeatherResponse> weatherCache;
    private Cache<String, String> keyCache;
    private CacheServiceImpl cacheService;

    private static final String DEFAULT_API_KEY = "default";

    @BeforeEach
    void setUp() {
        weatherCache = mock(Cache.class);
        keyCache = mock(Cache.class);
        cacheService = new CacheServiceImpl(weatherCache, keyCache, "my-default-key");
    }

    @Test
    void testPutAndGetWeather() {
        WeatherResponse weatherResponse = mock(WeatherResponse.class);
        String city = "London";

        cacheService.putWeather(city, weatherResponse);
        verify(weatherCache).put(city, weatherResponse);

        when(weatherCache.getIfPresent(city)).thenReturn(weatherResponse);
        WeatherResponse result = cacheService.getWeatherIfPresent(city);

        assertNotNull(result);
        assertEquals(weatherResponse, result);
    }

    @Test
    void testPutAndGetKey() {
        String username = "user1";
        String apiKey = "api-key-123";

        cacheService.putKey(username, apiKey);
        verify(keyCache).put(username, apiKey);

        when(keyCache.getIfPresent(username)).thenReturn(apiKey);
        String result = cacheService.getKeyIfPresent(username);

        assertNotNull(result);
        assertEquals(apiKey, result);
    }

    @Test
    void testGetDefaultApiKey() {
        when(keyCache.getIfPresent(DEFAULT_API_KEY)).thenReturn("my-default-key");
        String defaultKey = cacheService.getDefaultApiKey();

        assertNotNull(defaultKey);
        assertEquals("my-default-key", defaultKey);
    }

    @Test
    void testRemoveKey() {
        String username = "user1";
        cacheService.removeKey(username);
        verify(keyCache).invalidate(username);
    }
}