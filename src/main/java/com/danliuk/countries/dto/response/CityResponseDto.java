package com.danliuk.countries.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseDto {

    private Long id;

    private String name;

    private Long area;

    private Long population;
}
