package com.github.edsongustavotofolo.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenWeatherMapsConfig {

    public static final String API_KEY = "6801fe9e74c3fd9d5a5b0ea6b668d7af";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    @Bean
    public WebClient wheaterMapsApi() {
        return WebClient.create(BASE_URL);
    }
}
