package com.github.edsongustavotofolo.domain.thirdparty.openweathermap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Main {
    private int temp;
}
