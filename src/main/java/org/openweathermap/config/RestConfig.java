package org.openweathermap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Configuration
public class RestConfig {


    @Bean
    public RestClient weatherRestClient(@Value("${openweather.url}") String baseUrl) {
        return RestClient.create(baseUrl);
    }
}
