package org.openweathermap.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.config.WeatherScheduledCondition;
import org.openweathermap.service.CacheService;
import org.openweathermap.service.WeatherService;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Scheduler component that periodically fetches weather data for a predefined set of cities.
 * <p>
 * This scheduler runs only under the "scheduled" Spring profile and uses a fixed rate
 * configured in application properties.
 * </p>
 */
@Slf4j
@RequiredArgsConstructor
@Component
@Conditional(WeatherScheduledCondition.class)
public class WeatherFetchScheduler {

    /**
     * Default list of cities to fetch weather data for.
     */
    private static final Set<String> DEFAULT_CITIES = Set.of(
            "New York",
            " Rome",
            " Istanbul",
            "Paris",
            " Moscow",
            "Beijing",
            "London",
            "Berlin",
            "Amsterdam",
            "Washington"
    );

    private final WeatherService weatherService;
    private final CacheService cacheService;

    /**
     * Scheduled method that fetches weather data for each default city at a fixed interval.
     * <p>
     * Retrieves the default API key from the cache and calls the weather service for each city.
     * Logs an info message if fetching weather data fails for a specific city.
     * </p>
     */
    @Scheduled(fixedRateString = "${weather.scheduler.fixedRate}")
    public void fetchWeatherScheduler() {

        String defaultApiKey = cacheService.getKeyIfPresent("default");

        DEFAULT_CITIES.forEach(city -> {
                    try {
                        weatherService.fetchWeather(city, defaultApiKey);
                    } catch (Exception e) {
                        log.info("Failed to fetch weather for city {}, reason: {}", city, e.getMessage());
                    }
                }
        );
    }

}
