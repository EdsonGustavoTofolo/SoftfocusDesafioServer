package com.github.edsongustavotofolo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistReturnDTO {
    private Integer temperature;
    private String cityName;
    private Double longitude;
    private Double latitude;
    private List<String> trackNames;
    private Integer totalTracks;
    private Integer pageIndex;
    private Integer pageSize;
}
