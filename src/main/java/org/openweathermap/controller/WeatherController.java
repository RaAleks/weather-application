package org.openweathermap.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.dto.remote.WeatherResponse;
import org.openweathermap.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> fetchWeather(@RequestHeader("Api-Key") String apiKey,
                                                        @PathVariable String city) {
        return ResponseEntity.ok(weatherService.fetchWeather(apiKey, city));
    }

}
