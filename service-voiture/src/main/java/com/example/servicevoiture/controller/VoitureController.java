package com.example.servicevoiture.controller;

import com.example.servicevoiture.client.ClientService;
import com.example.servicevoiture.model.Client;
import com.example.servicevoiture.model.Voiture;
import com.example.servicevoiture.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Voiture> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        voitures.forEach(v -> v.setClient(clientService.findById(v.getClientId())));
        return voitures;
    }

    @GetMapping("/{id}")
    public Voiture findById(@PathVariable Long id) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture not found"));
        voiture.setClient(clientService.findById(voiture.getClientId()));
        return voiture;
    }

    @GetMapping("/client/{clientId}")
    public List<Voiture> findByClientId(@PathVariable Long clientId) {
        return voitureRepository.findByClientId(clientId);
    }

    @PostMapping
    public Voiture save(@RequestBody Voiture voiture) {
        return voitureRepository.save(voiture);
    }
}