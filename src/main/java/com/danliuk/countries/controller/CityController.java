package com.danliuk.countries.controller;

import com.danliuk.countries.dto.request.CityRequestDto;
import com.danliuk.countries.dto.response.CityResponseDto;
import com.danliuk.countries.repository.specificaton.SearchCriteria;
import com.danliuk.countries.service.CityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/filtered")
    public List<CityResponseDto> findByFilter(@RequestBody List<SearchCriteria> filters) {
        return cityService.findByFilter(filters);
    }

    @GetMapping
    public List<CityResponseDto> getAll() {
        return cityService.getAll();
    }

    @PostMapping
    public CityResponseDto create(@RequestBody CityRequestDto request) {
        return cityService.create(request);
    }

    @PutMapping("/{id}")
    public CityResponseDto update(@PathVariable Long id, @RequestBody CityRequestDto request) {
        return cityService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
