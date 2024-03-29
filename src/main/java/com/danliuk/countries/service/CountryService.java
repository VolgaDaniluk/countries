package com.danliuk.countries.service;


import com.danliuk.countries.dto.request.CountryRequestDto;
import com.danliuk.countries.dto.response.CountryResponseDto;
import com.danliuk.countries.exception.ItemNotFoundException;
import com.danliuk.countries.mapper.CountryMapper;
import com.danliuk.countries.model.Country;
import com.danliuk.countries.repository.CountryRepository;
import com.danliuk.countries.repository.specificaton.CountrySpecification;
import com.danliuk.countries.repository.specificaton.SearchCriteria;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountrySpecification countrySpecification;

    @Transactional(readOnly = true)
    public List<CountryResponseDto> getAll() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper.COUNTRY_MAPPER::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CountryResponseDto> findByFilter(List<SearchCriteria> criteriaList) {
        log.info("Getting countries with filtration");
        Specification<Country> specification = countrySpecification.build(criteriaList);

        return countryRepository.findAll(specification)
                .stream()
                .map(CountryMapper.COUNTRY_MAPPER::toResponseDto)
                .toList();

    }

    @Transactional
    public CountryResponseDto create(CountryRequestDto dto) {
        log.info("Create country {}", dto);
        Country country = CountryMapper.COUNTRY_MAPPER.toModel(dto);
        Country saved = countryRepository.save(country);
        return CountryMapper.COUNTRY_MAPPER.toResponseDto(saved);
    }

    @Transactional
    public CountryResponseDto update(CountryRequestDto dto, Long id) {
        log.info("Create country with id {}. {}", id, dto);
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Country wasn't found with id " + id));

        CountryMapper.COUNTRY_MAPPER.updateModel(dto, country);
        Country updated = countryRepository.save(country);

        return CountryMapper.COUNTRY_MAPPER.toResponseDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Delete country with id {}", id);
        countryRepository.deleteById(id);
    }
}
