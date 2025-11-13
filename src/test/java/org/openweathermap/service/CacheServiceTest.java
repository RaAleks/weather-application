package org.openweathermap.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openweathermap.dto.remote.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CacheServiceTest {

    private Cache<String, WeatherResponse> weatherCache;
    private Cache<String, String> keyCache;
    private CacheService cacheService;

    private static final String DEFAULT_KEY = "default";
    private static final String DEFAULT_API_KEY = "my-default-api-key";

    @BeforeEach
    void setUp() {
        weatherCache = mock(Cache.class);
        keyCache = mock(Cache.class);

        cacheService = new CacheService(weatherCache, keyCache, DEFAULT_API_KEY);
    }

    @Test
    void testGetDefaultApiKey() {
        when(keyCache.getIfPresent(DEFAULT_KEY)).thenReturn(DEFAULT_API_KEY);

        String apiKey = cacheService.getDefaultApiKey();

        assertEquals(DEFAULT_API_KEY, apiKey);
        verify(keyCache).getIfPresent(DEFAULT_KEY);
    }

    @Test
    void testPutAndGetWeather() {
        String city = "London";
        WeatherResponse weatherResponse = new WeatherResponse(
                new Coord(37, 55),
                List.of(new Weather(803, "Clouds", "broken clouds", "04n")),
                "stations",
                new Main(278, 274, 277, 278, 1014, 61, 1014, 995),
                10000,
                new Wind(5.95, 224, 10.7),
                null,
                new Clouds(84),
                1763057457L,
                new Sys(2, 2095214, "RU", 1763010045L, 1763040422L),
                10800,
                524901L,
                "Moscow",
                200
        );

        cacheService.putWeather(city, weatherResponse);
        verify(weatherCache).put(city, weatherResponse);

        when(weatherCache.getIfPresent(city)).thenReturn(weatherResponse);

        WeatherResponse cached = cacheService.getWeatherIfPresent(city);
        assertEquals(weatherResponse, cached);
        verify(weatherCache).getIfPresent(city);
    }

    @Test
    void testPutAndGetKey() {
        String username = "john";
        String key = "user-key";

        cacheService.putKey(username, key);
        verify(keyCache).put(username, key);

        when(keyCache.getIfPresent(username)).thenReturn(key);

        String cachedKey = cacheService.getKeyIfPresent(username);
        assertEquals(key, cachedKey);
        verify(keyCache).getIfPresent(username);
    }

    @Test
    void testRemoveKey() {
        String username = "alice";

        cacheService.removeKey(username);
        verify(keyCache).invalidate(username);
    }
}