package org.openweathermap.service;

import org.openweathermap.dto.remote.WeatherResponse;

public interface CacheService {

    String getKeyIfPresent(String username);

    void removeKey(String username);

    void putKey(String username, String key);

    String getDefaultApiKey();

    void putWeather(String city, WeatherResponse weatherResponse);

    WeatherResponse getWeatherIfPresent(String city);
}
