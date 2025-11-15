package org.openweathermap.service;

import org.openweathermap.dto.remote.WeatherResponse;

public interface WeatherService {

    WeatherResponse fetchWeather(String city,
                                 String apiKey);

    WeatherResponse fetchWithUsername(String city,
                                      String username);
}
