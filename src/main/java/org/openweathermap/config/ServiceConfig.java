package org.openweathermap.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.dto.remote.WeatherResponse;
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
    private final Integer keyCacheSize;

    public ServiceConfig(@Value("${weather.cache.expiration-minutes}") Integer weatherCacheExpiration,
                         @Value("${weather.cache.size}") Integer weatherCacheSize,
                         @Value("${weather.cache.key.size}") Integer keyCacheSize) {
        this.weatherCacheExpiration = weatherCacheExpiration;
        this.weatherCacheSize = weatherCacheSize;
        this.keyCacheSize = keyCacheSize;
    }

    @Bean
    public Cache<String, WeatherResponse> weatherCache() {
        log.info("Building cache with size {} and expiration {}", weatherCacheSize, weatherCacheExpiration);
        return Caffeine.newBuilder().expireAfterWrite(weatherCacheExpiration, TimeUnit.MINUTES).maximumSize(weatherCacheSize).build();
    }

    @Bean
    public Cache<String, String> keyCache() {
        log.info("Building key cache with size {}", keyCacheSize);
        return Caffeine.newBuilder().maximumSize(weatherCacheSize).build();
    }
}
