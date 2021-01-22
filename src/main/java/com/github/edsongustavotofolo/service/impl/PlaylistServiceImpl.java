package com.github.edsongustavotofolo.service.impl;

import com.github.edsongustavotofolo.domain.thirdparty.openweathermap.OpenWeatherMaps;
import com.github.edsongustavotofolo.service.IOpenWeatherMapService;
import com.github.edsongustavotofolo.service.IPlaylistService;
import com.github.edsongustavotofolo.service.ISpotifyService;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaylistServiceImpl implements IPlaylistService {

    private final IOpenWeatherMapService openWeatherMapService;
    private final ISpotifyService spotifyService;

    @Override
    public OpenWeatherMaps findWeatherMap(OpenWeatherMaps openWeatherMaps) {
        if (openWeatherMaps.getName() != null && !openWeatherMaps.getName().isEmpty()) {
            return openWeatherMapService.findByCityName(openWeatherMaps.getName());
        } else {
            return openWeatherMapService.findByCoordinates(openWeatherMaps.getCoord().getLat(), openWeatherMaps.getCoord().getLon());
        }
    }

    @Override
    public Paging<Track> findTracksByTemperature(Integer temperature, Integer offset, Integer pageSize) {
        Paging<Track> paging = null;
        if (temperature > 30) {
            paging = spotifyService.findPartyTracks(offset, pageSize);
        } else if (temperature > 15) {
            paging = spotifyService.findPopTracks(offset, pageSize);
        } else if (temperature > 10) {
            paging = spotifyService.findRockTracks(offset, pageSize);
        } else {
            paging = spotifyService.findClassicTracks(offset, pageSize);
        }
        return paging;
    }
}
