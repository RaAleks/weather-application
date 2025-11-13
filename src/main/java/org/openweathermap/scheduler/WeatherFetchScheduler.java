package org.openweathermap.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.service.CacheService;
import org.openweathermap.service.WeatherService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Profile("scheduled")
@Component
public class WeatherFetchScheduler {

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
