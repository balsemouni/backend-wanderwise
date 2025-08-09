package com.example.country_managment.country.repository;


import com.example.country_managment.country.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    List<Historique> findByUserId(Long userId);

    List<Historique> findByCountriesId(Long countryId);
}