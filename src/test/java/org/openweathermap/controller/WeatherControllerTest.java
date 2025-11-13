package org.openweathermap.controller;

import org.junit.jupiter.api.Test;
import org.openweathermap.controller.v1.WeatherController;
import org.openweathermap.dto.remote.*;
import org.openweathermap.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @Test
    void fetchWeather_ReturnsWeatherResponse() throws Exception {
        String apiKey = "dummy-key";
        String city = "Turin";

        WeatherResponse mockResponse = new WeatherResponse(
                new Coord(7.367, 45.133),
                List.of(new Weather(501, "Rain", "moderate rain", "10d")),
                "stations",
                new Main(284.2, 282.93, 283.06, 286.82, 1021, 60, 1021, 910),
                10000,
                new Wind(4.09, 121, 3.47),
                new Rain(2.73),
                new Clouds(83),
                1726660758L,
                new Sys(1, 6736, "IT", 1726636384L, 1726680975L),
                7200,
                3165523L,
                "Province of Turin",
                200
        );

        when(weatherService.fetchWeather(city, apiKey)).thenReturn(mockResponse);

        Weather weather = mockResponse.weather().get(0);
        Main main = mockResponse.main();
        Wind wind = mockResponse.wind();

        mockMvc.perform(get("/v1/weather/{city}/custom", city)
                        .header("Api-Key", apiKey)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(mockResponse.name()))
                .andExpect(jsonPath("$.temperature.temp").value(main.temp()))
                .andExpect(jsonPath("$.weather.main").value(weather.main()))
                .andExpect(jsonPath("$.weather.description").value(weather.description()))
                .andExpect(jsonPath("$.wind.speed").value(wind.speed()));
    }
}
