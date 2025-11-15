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

/**
 * Configuration class for service and cache setup.
 * <p>
 * Configures caches for storing weather data and API keys using Caffeine library.
 * Also enables Spring scheduling support.
 * </p>
 */
@Slf4j
@EnableScheduling
@Configuration
public class ServiceConfig {

    /** Expiration time for weather data cache entries, in minutes. */
    private final Integer weatherCacheExpiration;

    /** Maximum size of the weather data cache. */
    private final Integer weatherCacheSize;

    /** Maximum size of the key cache. */
    private final Integer keyCacheSize;

    /**
     * Constructor for the service configuration.
     *
     * @param weatherCacheExpiration Expiration time for weather cache entries (minutes)
     * @param weatherCacheSize       Maximum size of weather data cache
     * @param keyCacheSize           Maximum size of key cache
     */
    public ServiceConfig(@Value("${weather.cache.expiration-minutes}") Integer weatherCacheExpiration,
                         @Value("${weather.cache.size}") Integer weatherCacheSize,
                         @Value("${weather.cache.key.size}") Integer keyCacheSize) {
        this.weatherCacheExpiration = weatherCacheExpiration;
        this.weatherCacheSize = weatherCacheSize;
        this.keyCacheSize = keyCacheSize;
    }

    /**
     * Creates and configures a cache for weather data.
     * <p>
     * The cache has a maximum size and expiration time after write.
     * </p>
     *
     * @return a {@link Cache} instance for weather data
     */
    @Bean
    public Cache<String, WeatherResponse> weatherCache() {
        log.info("Building cache with size {} and expiration {}", weatherCacheSize, weatherCacheExpiration);
        return Caffeine.newBuilder()
                .expireAfterWrite(weatherCacheExpiration, TimeUnit.MINUTES)
                .maximumSize(weatherCacheSize)
                .build();
    }

    /**
     * Creates and configures a cache for API keys.
     * <p>
     * The cache has a maximum size limit only.
     * </p>
     *
     * @return a {@link Cache} instance for keys
     */
    @Bean
    public Cache<String, String> keyCache() {
        log.info("Building key cache with size {}", keyCacheSize);
        return Caffeine.newBuilder()
                .maximumSize(keyCacheSize)
                .build();
    }
}