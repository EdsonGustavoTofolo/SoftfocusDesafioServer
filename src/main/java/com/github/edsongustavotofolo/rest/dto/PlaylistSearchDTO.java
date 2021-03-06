package com.github.edsongustavotofolo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSearchDTO {
    private String cityName;
    private Double latitude;
    private Double longitude;
    private Integer offset;
    private Integer pageSize;
}
