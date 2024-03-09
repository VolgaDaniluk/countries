package com.danliuk.countries.service;


import com.danliuk.countries.dto.request.CountryRequestDto;
import com.danliuk.countries.dto.response.CountryResponseDto;
import com.danliuk.countries.exception.ItemNotFoundException;
import com.danliuk.countries.mapper.CountryMapper;
import com.danliuk.countries.model.Country;
import com.danliuk.countries.repository.CountryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @Transactional
    public List<CountryResponseDto> getAll() {
        return countryRepository.findAll().stream()
                .map(CountryMapper.COUNTRY_MAPPER::toResponseDto)
                .toList();
    }

    @Transactional
    public CountryResponseDto create(CountryRequestDto dto) {
        Country country = CountryMapper.COUNTRY_MAPPER.toModel(dto);
        Country saved = countryRepository.save(country);
        return CountryMapper.COUNTRY_MAPPER.toResponseDto(saved);
    }

    @Transactional
    public CountryResponseDto update(CountryRequestDto dto, Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Country wasn't found with id " + id));

        CountryMapper.COUNTRY_MAPPER.updateModel(dto, country);
        Country updated = countryRepository.save(country);

        return CountryMapper.COUNTRY_MAPPER.toResponseDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
