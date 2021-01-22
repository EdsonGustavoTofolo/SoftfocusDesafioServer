package com.github.edsongustavotofolo.service;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;

public interface ISpotifyService {
    Paging<Track> findPartyTracks(Integer offset, Integer pageSize);
    Paging<Track> findPopTracks(Integer offset, Integer pageSize);
    Paging<Track> findRockTracks(Integer offset, Integer pageSize);
    Paging<Track> findClassicTracks(Integer offset, Integer pageSize);
}
