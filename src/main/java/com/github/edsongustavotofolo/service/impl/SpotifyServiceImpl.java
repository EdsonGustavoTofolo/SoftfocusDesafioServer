package com.github.edsongustavotofolo.service.impl;

import com.github.edsongustavotofolo.service.ISpotifyService;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class SpotifyServiceImpl implements ISpotifyService {

    private final SpotifyApi spotifyApiBuilder;

    @Override
    public Paging<Track> findPartyTracks(Integer offset, Integer pageSize) {
        return findTracks("party", offset, pageSize);
    }

    @Override
    public Paging<Track> findPopTracks(Integer offset, Integer pageSize) {
        return findTracks("pop", offset, pageSize);
    }

    @Override
    public Paging<Track> findRockTracks(Integer offset, Integer pageSize) {
        return findTracks("rock", offset, pageSize);
    }

    @Override
    public Paging<Track> findClassicTracks(Integer offset, Integer pageSize) {
        return findTracks("classic", offset, pageSize);
    }

    private Paging<Track> findTracks(String genre, Integer offset, Integer pageSize) {
        try {
            return spotifyApiBuilder.searchTracks(genre).limit(pageSize).offset(offset).build().execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
