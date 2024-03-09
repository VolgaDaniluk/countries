package com.danliuk.countries.controller;

import com.danliuk.countries.dto.request.CountryRequestDto;
import com.danliuk.countries.dto.response.CountryResponseDto;
import com.danliuk.countries.service.CountryService;
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
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryResponseDto> getAll() {
        return countryService.getAll();
    }

    @PostMapping
    public CountryResponseDto create(@RequestBody CountryRequestDto request) {
        return countryService.create(request);
    }

    @PutMapping("/{id}")
    public CountryResponseDto update(@PathVariable Long id, @RequestBody CountryRequestDto request) {
        return countryService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        countryService.delete(id);
    }
}
