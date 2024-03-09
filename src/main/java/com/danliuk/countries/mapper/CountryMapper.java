package com.danliuk.countries.mapper;

import com.danliuk.countries.dto.request.CountryRequestDto;
import com.danliuk.countries.dto.response.CountryResponseDto;
import com.danliuk.countries.model.Country;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CountryMapper {

    CountryMapper COUNTRY_MAPPER = Mappers.getMapper(CountryMapper.class);

    Country toModel(CountryRequestDto requestDto);

    CountryResponseDto toResponseDto(Country country);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(CountryRequestDto dto, @MappingTarget Country country);
}
