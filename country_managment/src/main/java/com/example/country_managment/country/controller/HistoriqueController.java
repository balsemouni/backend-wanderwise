package com.example.country_managment.country.controller;


import com.example.country_managment.country.model.Historique;
import com.example.country_managment.country.service.HistoriqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiques")
public class HistoriqueController {
    private final HistoriqueService historiqueService;

    public HistoriqueController(HistoriqueService historiqueService) {
        this.historiqueService = historiqueService;
    }

    @PostMapping
    public Historique createHistorique(@RequestBody HistoriqueRequest request) {
        return historiqueService.createHistorique(request.userId(), request.countryIds());
    }

    @GetMapping("/user/{userId}")
    public List<Historique> getHistoriquesByUser(@PathVariable Long userId) {
        return historiqueService.getHistoriqueByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorique(@PathVariable Long id) {
        historiqueService.deleteHistorique(id);
    }

    public record HistoriqueRequest(Long userId, List<Long> countryIds) {}
}