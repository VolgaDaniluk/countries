package com.danliuk.countries.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDto {

    private String name;

    private Long area;

    private Long population;

    private Long countryId;
}
