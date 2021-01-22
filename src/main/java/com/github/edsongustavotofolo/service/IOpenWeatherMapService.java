package com.github.edsongustavotofolo.service;

import com.github.edsongustavotofolo.domain.thirdparty.openweathermap.OpenWeatherMaps;

public interface IOpenWeatherMapService {

    OpenWeatherMaps findByCoordinates(Double latitude, Double longitude);
    OpenWeatherMaps findByCityName(String cityName);
}
