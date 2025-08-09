package com.example.country_managment.country.service;
import com.example.country_managment.country.model.Country;
import com.example.country_managment.country.model.Historique;
import com.example.country_managment.country.repository.CountryRepository;
import com.example.country_managment.country.repository.HistoriqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HistoriqueService {
    private final HistoriqueRepository historiqueRepository;
    private final CountryRepository countryRepository;

    public HistoriqueService(HistoriqueRepository historiqueRepository,
                             CountryRepository countryRepository) {
        this.historiqueRepository = historiqueRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional
    public Historique createHistorique(Long userId, List<Long> countryIds) {
        // Validate input
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (countryIds == null || countryIds.isEmpty()) {
            throw new IllegalArgumentException("Country IDs list cannot be empty");
        }

        // Check for duplicates
        Set<Long> uniqueCountryIds = new HashSet<>(countryIds);
        if (uniqueCountryIds.size() != countryIds.size()) {
            throw new IllegalArgumentException("Duplicate country IDs are not allowed");
        }

        // Fetch countries
        List<Country> countries = countryRepository.findAllById(countryIds);

        // Verify all countries exist
        if (countries.size() != countryIds.size()) {
            List<Long> missingIds = countryIds.stream()
                    .filter(id -> countries.stream().noneMatch(c -> c.getId().equals(id)))
                    .toList();
            throw new RuntimeException("Countries not found: " + missingIds);
        }

        // Create and save historique
        Historique historique = new Historique();
        historique.setUserId(userId);
        historique.setCountries(countries);

        return historiqueRepository.save(historique);
    }

    @Transactional(readOnly = true)
    public List<Historique> getHistoriqueByUserId(Long userId) {
        return historiqueRepository.findByUserId(userId);
    }

    public void deleteHistorique(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        historiqueRepository.deleteById(id);
    }
}