# OpenWeather Java SDK

A lightweight Java SDK for fetching weather data from **OpenWeather API** with caching and scheduled updates.

---

## Table of Contents

* [Overview](#overview)
* [Installation](#installation)
* [Configuration](#configuration)
* [Usage](#usage)

  * [Fetch weather with default API key](#fetch-weather-with-default-api-key)
  * [Fetch weather with custom API key](#fetch-weather-with-custom-api-key)
  * [Fetch weather with username-associated key](#fetch-weather-with-username-associated-key)
* [Caching](#caching)
* [API Key Management](#api-key-management)
* [Error Handling](#error-handling)
* [Examples](#examples)
* [DTO Reference](#dto-reference)

---

## Overview

This SDK provides:

* Fetching weather data for cities using **OpenWeather API**.
* Built-in **caching** to avoid unnecessary API calls.
* Support for **default**, **custom**, and **username-associated API keys**.
* **Scheduled updates** to refresh weather data automatically.
* Centralized **error handling** with clear JSON responses.

---

## Installation

Add the SDK to your Maven or Gradle project:

**Maven:**

```xml
<dependency>
    <groupId>org.openweathermap</groupId>
    <artifactId>weather-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle:**

```gradle
implementation 'org.openweathermap:weather-sdk:1.0.0'
```

---

## Configuration

Configure your application in `application.yml`:

```yaml
server:
  servlet:
    context-path: /api

openweather:
  url: http://api.openweathermap.org
  api-key: YOUR_DEFAULT_API_KEY

weather:
  cache:
    expiration-minutes: 5   # Duration (in minutes) that weather data stays valid in cache
    size: 10                # Maximum number of entries in cache
  scheduled: true            # Enable scheduled updates
  scheduler:
    fixedRate: 600000        # Interval in milliseconds for scheduled cache refresh (10 minutes)
```

**Configuration parameters explained:**

| Property                           | Type      | Description                                                 |
| ---------------------------------- | --------- | ----------------------------------------------------------- |
| `server.servlet.context-path`      | `String`  | Prefix for all REST endpoints (e.g., `/api/v1/weather/...`) |
| `openweather.url`                  | `String`  | Base URL for OpenWeather API                                |
| `openweather.api-key`              | `String`  | Default API key for requests                                |
| `weather.cache.expiration-minutes` | `Integer` | Cache expiration time in minutes                            |
| `weather.cache.size`               | `Integer` | Maximum number of cached weather entries                    |
| `weather.scheduled`                | `Boolean` | Enable or disable scheduled weather updates                 |
| `weather.scheduler.fixedRate`      | `Long`    | Interval in milliseconds for automatic cache refresh        |

---

## Error Handling

The SDK provides centralized exception handling:

* **400 Bad Request** for invalid arguments (`MethodArgumentNotValidException`).

**Example JSON response:**

```json
{
  "timestamp": "2025-11-13T12:34:56",
  "message": "Invalid request"
}
```

---

## Example Weather Response

```json
{
  "weather": {
    "main": "Clouds",
    "description": "broken clouds"
  },
  "temperature": {
    "temp": 278.42,
    "feels_like": 274.41
  },
  "visibility": 10000,
  "wind": {
    "speed": 5.95
  },
  "datetime": 1763057922,
  "sys": {
    "sunrise": 1763010046,
    "sunset": 1763040422
  },
  "timezone": 10800,
  "name": "Moscow"
}
```

---

## API Key Management

| Endpoint                            | Method | Description                                  |
| ----------------------------------- | ------ | -------------------------------------------- |
| `/v1/api-key/{apiKey}?username=...` | PUT    | Add a new API key associated with a username |
| `/v1/api-key?username=...`          | DELETE | Remove API key associated with a username    |

**Example Usage:**

```bash
# Add API key for user "john"
PUT /api/v1/api-key/MY_KEY?username=john

# Remove API key for user "john"
DELETE /api/v1/api-key?username=john
```

---

## Caching

* Weather responses are cached using **Caffeine**.
* Cached data is considered **up-to-date if less than 10 minutes old**.
* Scheduled updates fetch weather periodically based on `fixedRate`.

---

## Usage Examples

```java
String city = "London";

// Using default API key
WeatherResponseDto defaultWeather = WeatherMapper.toDto(
    weatherService.fetchWeather(city, null)
);

// Using custom API key
WeatherResponseDto customWeather = WeatherMapper.toDto(
    weatherService.fetchWeather(city, "MY_CUSTOM_KEY")
);

// Using username-associated key
WeatherResponseDto userWeather = WeatherMapper.toDto(
    weatherService.fetchWithUsername(city, "john")
);

System.out.println(defaultWeather.name());
System.out.println(customWeather.weather().description());
System.out.println(userWeather.temperature().feels_like());
```

---

## DTO Reference

**WeatherResponseDto**

| Field         | Type             | Description                   |
| ------------- | ---------------- | ----------------------------- |
| `weather`     | `WeatherDto`     | Weather condition info        |
| `temperature` | `TemperatureDto` | Temperature data              |
| `visibility`  | `int`            | Visibility in meters          |
| `wind`        | `WindDto`        | Wind information              |
| `datetime`    | `long`           | Timestamp of the weather data |
| `sys`         | `SysDto`         | Sunrise and sunset info       |
| `timezone`    | `int`            | Timezone offset in seconds    |
| `name`        | `String`         | City name                     |

**WeatherDto**

| Field         | Type     | Description            |
| ------------- | -------- | ---------------------- |
| `main`        | `String` | Main weather condition |
| `description` | `String` | Detailed description   |

**TemperatureDto**

| Field        | Type     | Description            |
| ------------ | -------- | ---------------------- |
| `temp`       | `double` | Current temperature    |
| `feels_like` | `double` | Feels like temperature |

**WindDto**

| Field   | Type     | Description       |
| ------- | -------- | ----------------- |
| `speed` | `double` | Wind speed in m/s |

**SysDto**

| Field     | Type   | Description       |
| --------- | ------ | ----------------- |
| `sunrise` | `long` | Sunrise timestamp |
| `sunset`  | `long` | Sunset timestamp  |

---

This README provides full guidance on **installation, configuration, usage, API key management, caching, error handling, examples, and DTO reference** for the OpenWeather Java SDK.
