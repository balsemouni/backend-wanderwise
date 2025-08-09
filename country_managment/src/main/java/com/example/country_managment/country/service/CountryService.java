package com.example.country_managment.country.service;

import com.example.country_managment.country.model.Continent;
import com.example.country_managment.country.model.Country;
import com.example.country_managment.country.model.Historique;
import com.example.country_managment.country.repository.CountryRepository;
import com.example.country_managment.country.repository.HistoriqueRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    private final HistoriqueRepository historiqueRepository;
    private final CountryRepository countryRepository;

    public CountryService(HistoriqueRepository historiqueRepository, CountryRepository countryRepository) {
        this.historiqueRepository = historiqueRepository;
        this.countryRepository = countryRepository;
    }

    public Country updateCountry(Long id, String name, String imagepath, String description, Continent continent) {
        Country country = countryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Country not found with id: " + id));

        if (name != null) country.setName(name);
        if (imagepath != null) country.setImagepath(imagepath);
        if (description != null) country.setDescription(description);
        if (continent != null) country.setContinent(continent);

        return countryRepository.save(country);
    }

    public List<Country> getCountriesByContinent(Continent continent) {
        return countryRepository.findByContinent(continent);
    }

    public List<Country> searchCountriesByName(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Country> createCountries(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country createCountry(String name, String imagepath, String description, Continent continent) {
        Country country = new Country(name, imagepath, description, continent);
        return countryRepository.save(country);
    }

    public void deleteCountry(Long countryId) {
        List<Historique> historiques = historiqueRepository.findByCountriesId(countryId);
        historiques.forEach(historique -> {
            historique.getCountries().removeIf(country -> country.getId().equals(countryId));
            historiqueRepository.save(historique);
        });
        countryRepository.deleteById(countryId);
    }
}