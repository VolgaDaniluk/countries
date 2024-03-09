package com.danliuk.countries.mapper;

import com.danliuk.countries.dto.request.CityRequestDto;
import com.danliuk.countries.dto.response.CityResponseDto;
import com.danliuk.countries.model.City;
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
public interface CityMapper {

    CityMapper CITY_MAPPER = Mappers.getMapper(CityMapper.class);

    City toModel(CityRequestDto requestDto);

    CityResponseDto toResponseDto(City city);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(CityRequestDto dto, @MappingTarget City city);
}
