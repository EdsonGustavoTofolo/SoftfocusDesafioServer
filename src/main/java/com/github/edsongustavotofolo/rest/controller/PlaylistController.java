package com.github.edsongustavotofolo.rest.controller;

import com.github.edsongustavotofolo.domain.entity.Request;
import com.github.edsongustavotofolo.domain.repository.IRequestRepository;
import com.github.edsongustavotofolo.domain.thirdparty.openweathermap.Coord;
import com.github.edsongustavotofolo.domain.thirdparty.openweathermap.OpenWeatherMaps;
import com.github.edsongustavotofolo.rest.dto.PlaylistReturnDTO;
import com.github.edsongustavotofolo.rest.dto.PlaylistSearchDTO;
import com.github.edsongustavotofolo.service.IPlaylistService;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private final IPlaylistService playlistService;
    private final IRequestRepository requestRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public PlaylistReturnDTO get(PlaylistSearchDTO dto, HttpServletRequest servletRequest) {
        Request request = Request.builder()
                .cityName(dto.getCityName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .ip(servletRequest.getRemoteAddr())
                .build();

        requestRepository.save(request);

        System.out.println(dto.toString());
        OpenWeatherMaps openWeatherMapSearching = OpenWeatherMaps.builder()
                .name(dto.getCityName())
                .coord(Coord.builder().lat(dto.getLatitude()).lon(dto.getLongitude()).build())
                .build();

        OpenWeatherMaps weatherMapResult = playlistService.findWeatherMap(openWeatherMapSearching);

        Paging<Track> trackPaging = playlistService.findTracksByTemperature(
                weatherMapResult.getMain().getTemp(), dto.getOffset(), dto.getPageSize());

        List<String> trackNames = Arrays.stream(trackPaging.getItems()).map(Track::getName).collect(Collectors.toList());

        PlaylistReturnDTO returnDTO = PlaylistReturnDTO.builder()
                .cityName(weatherMapResult.getName())
                .latitude(weatherMapResult.getCoord().getLat())
                .longitude(weatherMapResult.getCoord().getLon())
                .temperature(weatherMapResult.getMain().getTemp())
                .trackNames(trackNames)
                .totalTracks(trackPaging.getTotal())
                .pageIndex(dto.getOffset())
                .pageSize(dto.getPageSize())
                .build();
        System.out.println(returnDTO.toString());
        return returnDTO;
    }
}
