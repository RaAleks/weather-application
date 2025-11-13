package org.openweathermap.mapper;

import org.junit.jupiter.api.Test;
import org.openweathermap.dto.*;
import org.openweathermap.dto.remote.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherMapperTest {

    @Test
    void testToDto() {
        // Prepare WeatherResponse
        Weather weather = new Weather(803, "Clouds", "broken clouds", "04n");
        Main main = new Main(278.46, 274.46, 277.29, 278.57, 1014, 61, 1014, 995);
        Wind wind = new Wind(5.95, 224, 10.7);
        Sys sys = new Sys(2, 2095214, "RU", 1763010045L, 1763040422L);
        WeatherResponse response = new WeatherResponse(
                new Coord(37, 55),
                List.of(weather),
                "stations",
                main,
                10000,
                wind,
                null,
                null,
                1763057457L,
                sys,
                10800,
                524901L,
                "Moscow",
                200
        );

        // Map to DTO
        WeatherResponseDto dto = WeatherMapper.toDto(response);

        // Assertions using original WeatherResponse
        assertNotNull(dto);

        WeatherDto weatherDto = dto.weather();
        assertEquals(response.weather().get(0).main(), weatherDto.main());
        assertEquals(response.weather().get(0).description(), weatherDto.description());

        TemperatureDto tempDto = dto.temperature();
        assertEquals(response.main().temp(), tempDto.temp());
        assertEquals(response.main().feels_like(), tempDto.feels_like());

        WindDto windDto = dto.wind();
        assertEquals(response.wind().speed(), windDto.speed());

        SysDto sysDto = dto.sys();
        assertEquals(response.sys().sunrise(), sysDto.sunrise());
        assertEquals(response.sys().sunset(), sysDto.sunset());

        // Other fields
        assertEquals(response.visibility(), dto.visibility());
        assertEquals(response.dt(), dto.datetime());
        assertEquals(response.timezone(), dto.timezone());
        assertEquals(response.name(), dto.name());
    }

    @Test
    void testToDtoWithNull() {
        WeatherResponseDto dto = WeatherMapper.toDto(null);
        assertNull(dto);
    }
}
