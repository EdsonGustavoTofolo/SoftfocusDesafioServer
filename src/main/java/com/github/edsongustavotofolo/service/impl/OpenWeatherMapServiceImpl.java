package com.github.edsongustavotofolo.service.impl;

import com.github.edsongustavotofolo.domain.thirdparty.openweathermap.OpenWeatherMaps;
import com.github.edsongustavotofolo.rest.config.OpenWeatherMapsConfig;
import com.github.edsongustavotofolo.service.IOpenWeatherMapService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class OpenWeatherMapServiceImpl implements IOpenWeatherMapService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);

    private final WebClient wheaterMapsApi;

    private String cityName;
    private Double latitude;
    private Double longitude;

    @Override
    public OpenWeatherMaps findByCoordinates(Double latitude, Double longitude) {
        this.cityName = "";
        this.latitude = latitude;
        this.longitude = longitude;
        return executeRequest();
    }

    @Override
    public OpenWeatherMaps findByCityName(String cityName) {
        this.cityName = cityName;
        return executeRequest();
    }

    private OpenWeatherMaps executeRequest() {
        return wheaterMapsApi
                .method(HttpMethod.GET)
                .uri(this::buildParams)
                .retrieve()
                .bodyToMono(OpenWeatherMaps.class)
                .block(REQUEST_TIMEOUT);
    }

    private URI buildParams(UriBuilder uriBuilder) {
        if (cityName != null && !cityName.isEmpty()) {
            uriBuilder = uriBuilder.queryParam("q", cityName);
        } else {
            uriBuilder = uriBuilder
                    .queryParam("lat", latitude)
                    .queryParam("lon", longitude);
        }
        return uriBuilder
                .queryParam("units", "metric")
                .queryParam("appid", OpenWeatherMapsConfig.API_KEY)
                .build();
    }


}
