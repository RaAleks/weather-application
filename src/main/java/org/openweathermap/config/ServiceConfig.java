package org.openweathermap.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.dto.CityWeatherInfo;
import org.openweathermap.dto.remote.WeatherResponse;
import org.openweathermap.scheduler.WeatherFetchScheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.TimeUnit;

@Slf4j
@EnableScheduling
@Configuration
public class ServiceConfig {


    private final Integer weatherCacheExpiration;
    private final Integer weatherCacheSize;

    public ServiceConfig(@Value("${weather.cache.expiration-minutes}") Integer weatherCacheExpiration,
                         @Value("${weather.cache.size}") Integer weatherCacheSize) {
        this.weatherCacheExpiration = weatherCacheExpiration;
        this.weatherCacheSize = weatherCacheSize;
    }

    @Bean
    public Cache<String, WeatherResponse> caffeineCache() {
        log.info("Building cache with size {} and expiration {}", weatherCacheSize, weatherCacheExpiration);
        return Caffeine.newBuilder()
                .expireAfterWrite(weatherCacheExpiration, TimeUnit.MINUTES)
                .maximumSize(weatherCacheSize)
                .build();
    }

    @Bean
    public WeatherFetchScheduler buildScheduler(@Value("${weather.scheduled}") Boolean weatherScheduled) {

        String logMsg = String.format("Application weather scheduling %s", weatherScheduled ? "enabled" : "disabled");

        if (weatherScheduled) {
            log.info(logMsg);
            return new WeatherFetchScheduler(caffeineCache());
        }
        log.info(logMsg);
        return null;
    }
}
