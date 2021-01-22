package com.github.edsongustavotofolo.service;

import com.github.edsongustavotofolo.domain.thirdparty.openweathermap.OpenWeatherMaps;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;

public interface IPlaylistService {
    OpenWeatherMaps findWeatherMap(OpenWeatherMaps openWeatherMaps);
    Paging<Track> findTracksByTemperature(Integer temperature, Integer offset, Integer pageSize);
}
