package org.openweathermap.service;

import lombok.RequiredArgsConstructor;
import org.openweathermap.dto.remote.LocationResponse;
import org.openweathermap.dto.remote.WeatherResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GeoDecoderService {

    private final RestClient weatherRestClient;

    public List<LocationResponse> decodeCity(String city, String apiKey) {
        String url = String.format("/geo/1.0/direct?q=%s&limit=1&appid=%s", city, apiKey);

        return weatherRestClient.get().uri(url).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    public WeatherResponse fetchWeather(double latitude,
                                        double longitude,
                                        String apiKey) {

        String url = String.format("/data/2.5/weather?lat=%s&lon=%s&appid=%s", latitude, longitude, apiKey);

        return weatherRestClient.get().uri(url).retrieve().body(WeatherResponse.class);
    }
}
