package com.danliuk.countries.service;

import com.danliuk.countries.dto.request.CityRequestDto;
import com.danliuk.countries.dto.response.CityResponseDto;
import com.danliuk.countries.exception.ItemNotFoundException;
import com.danliuk.countries.mapper.CityMapper;
import com.danliuk.countries.model.City;
import com.danliuk.countries.repository.CityRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional
    public List<CityResponseDto> getAll() {
        return cityRepository.findAll().stream()
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
