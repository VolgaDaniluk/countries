package com.danliuk.countries.service;

import com.danliuk.countries.dto.request.CityRequestDto;
import com.danliuk.countries.dto.response.CityResponseDto;
import com.danliuk.countries.exception.ItemNotFoundException;
import com.danliuk.countries.mapper.CityMapper;
import com.danliuk.countries.model.City;
import com.danliuk.countries.repository.CityRepository;
import com.danliuk.countries.repository.specificaton.CitySpecification;
import com.danliuk.countries.repository.specificaton.SearchCriteria;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CitySpecification citySpecification;

    @Transactional(readOnly = true)
    public List<CityResponseDto> getAll() {
        return cityRepository.findAll().stream()
                .map(CityMapper.CITY_MAPPER::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CityResponseDto> findByFilter(List<SearchCriteria> criteriaList) {
        Specification<City> specification = citySpecification.build(criteriaList);

        return cityRepository.findAll(specification)
                .stream()
                .map(CityMapper.CITY_MAPPER::toResponseDto)
                .toList();

    }

    @Transactional
    public CityResponseDto create(CityRequestDto dto) {
        City city = CityMapper.CITY_MAPPER.toModel(dto);
        City saved = cityRepository.save(city);
        return CityMapper.CITY_MAPPER.toResponseDto(saved);
    }

    @Transactional
    public CityResponseDto update(CityRequestDto dto, Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("City wasn't found with id " + id));

        CityMapper.CITY_MAPPER.updateModel(dto, city);
        City updated = cityRepository.save(city);
        return CityMapper.CITY_MAPPER.toResponseDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
