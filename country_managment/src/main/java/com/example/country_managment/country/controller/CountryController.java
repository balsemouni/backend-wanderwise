package com.example.country_managment.country.controller;

import com.example.country_managment.country.model.Continent;
import com.example.country_managment.country.model.Country;
import com.example.country_managment.country.service.CountryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    public record CountryRequest(String name, String imagepath, String description, Continent continent) {}

    @PostMapping
    public Country createCountry(@RequestBody CountryRequest request) {
        return countryService.createCountry(
                request.name(),
                request.imagepath(),
                request.description(),
                request.continent()
        );
    }

    public record CountryUpdateRequest(String name, String imagepath, String description, Continent continent) {}

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Long id, @RequestBody CountryUpdateRequest request) {
        return countryService.updateCountry(
                id,
                request.name(),
                request.imagepath(),
                request.description(),
                request.continent()
        );
    }

    @GetMapping("/continent/{continent}")
    public List<Country> getCountriesByContinent(@PathVariable Continent continent) {
        return countryService.getCountriesByContinent(continent);
    }

    @GetMapping("/search")
    public List<Country> searchCountries(@RequestParam String name) {
        return countryService.searchCountriesByName(name);
    }

    public record BulkCountryRequest(List<CountryRequest> countries) {}

    @PostMapping("/bulk")
    public List<Country> createCountries(@RequestBody BulkCountryRequest request) {
        List<Country> countries = request.countries().stream()
                .map(cr -> new Country(
                        cr.name(),
                        cr.imagepath(),
                        cr.description(),
                        cr.continent()
                ))
                .toList();
        return countryService.createCountries(countries);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }
}