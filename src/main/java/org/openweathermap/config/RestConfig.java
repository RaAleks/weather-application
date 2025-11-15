package org.openweathermap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuration class for REST clients.
 * <p>
 * Creates beans for working with external REST services, such as OpenWeather API.
 * </p>
 */
@RequiredArgsConstructor
@Configuration
public class RestConfig {

    /**
     * Creates and configures a REST client for OpenWeather API.
     *
     * @param baseUrl the base URL of the OpenWeather service
     * @return a {@link RestClient} instance configured with the given base URL
     */
    @Bean
    public RestClient weatherRestClient(@Value("${openweather.url}") String baseUrl) {
        return RestClient.create(baseUrl);
    }
}