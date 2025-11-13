package org.openweathermap.scheduler;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.openweathermap.dto.remote.WeatherResponse;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class WeatherFetchScheduler {

    private final Cache<String, WeatherResponse> caffeineCache;

    @Scheduled(fixedRate = 5000)
    public void fetchWeatherScheduler() {

    }

}
