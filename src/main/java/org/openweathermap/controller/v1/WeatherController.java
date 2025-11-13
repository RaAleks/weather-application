package org.openweathermap.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.dto.WeatherResponseDto;
import org.openweathermap.mapper.WeatherMapper;
import org.openweathermap.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for fetching weather information.
 * <p>
 * Supports fetching weather using:
 * - a custom API key,
 * - a username-associated API key,
 * - the default API key.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/weather")
@Tag(name = "Weather API", description = "Operations to fetch weather information for cities")
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Fetch weather information for a city using a custom API key.
     *
     * @param apiKey the custom API key to use for fetching weather
     * @param city   the name of the city
     * @return weather information for the requested city
     */
    @Operation(summary = "Fetch weather with custom API key",
            description = "Returns weather information for the specified city using a provided API key")
    @GetMapping("/{city}/custom")
    public ResponseEntity<WeatherResponseDto> fetchWeatherWithApiKey(
            @Parameter(description = "Custom API key", required = true)
            @RequestHeader("Api-Key") String apiKey,
            @Parameter(description = "City name", required = true)
            @PathVariable String city) {

        return ResponseEntity.ok(
                WeatherMapper.toDto(weatherService.fetchWeather(city, apiKey))
        );
    }

    /**
     * Fetch weather information for a city using a username-associated API key.
     *
     * @param username the username associated with an API key
     * @param city     the name of the city
     * @return weather information for the requested city
     */
    @Operation(summary = "Fetch weather with username",
            description = "Returns weather information for the specified city using an API key associated with the username")
    @GetMapping("/{city}/username")
    public ResponseEntity<WeatherResponseDto> fetchWeatherWithUsername(
            @Parameter(description = "Username associated with API key", required = true)
            @RequestParam("username") String username,
            @Parameter(description = "City name", required = true)
            @PathVariable String city) {

        return ResponseEntity.ok(
                WeatherMapper.toDto(weatherService.fetchWithUsername(city, username))
        );
    }

    /**
     * Fetch weather information for a city using the default API key.
     *
     * @param city the name of the city
     * @return weather information for the requested city
     */
    @Operation(summary = "Fetch weather with default API key",
            description = "Returns weather information for the specified city using the default API key")
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponseDto> fetchWeatherWithDefaultKey(
            @Parameter(description = "City name", required = true)
            @PathVariable String city) {

        return ResponseEntity.ok(
                WeatherMapper.toDto(weatherService.fetchWeather(city, null))
        );
    }
}
