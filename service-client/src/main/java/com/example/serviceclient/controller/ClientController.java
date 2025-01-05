package com.example.serviceclient.controller;

import com.example.serviceclient.client.ClientService;
import com.example.serviceclient.model.Client;
import com.example.serviceclient.repository.ClientRepository;
import com.example.serviceclient.client.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VoitureService voitureService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/{id}/voitures/resttemplate")
    public List<Object> getClientVoituresRestTemplate(@PathVariable Long id) {
        String url = "http://localhost:8888/voitures/client/" + id; // Using localhost:8888 for service-voiture
        return restTemplate.getForObject(url, List.class);
    }

    @GetMapping("/voitures/resttemplate")
    public List<Object> getAllVoituresRestTemplate() {
        String url = "http://localhost:8888/voitures"; // Using localhost:8888 for service-voiture
        return restTemplate.getForObject(url, List.class);
    }

    @GetMapping("/{id}/voitures/feign")
    public List<Object> getClientVoituresFeign(@PathVariable Long id) {
        return voitureService.getVoituresByClientId(id);
    }

    @GetMapping("/voitures/feign")
    public List<Object> getAllVoituresFeign() {
        return voitureService.getAllVoitures();
    }

    @GetMapping("/{id}/voitures/webclient")
    public List<Object> getClientVoituresWebClient(@PathVariable Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8888/voitures/client/" + id) // Using localhost:8888 for service-voiture
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    @GetMapping("/voitures/webclient")
    public List<Object> getAllVoituresWebClient() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8888/voitures") // Using localhost:8888 for service-voiture
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    @GetMapping("/feign")
    public List<Client> getAllClientsFeign() {
        return clientService.findAll(); // Use the injected instance
    }

    @GetMapping("/feign/{id}")
    public Client getClientByIdFeign(@PathVariable Long id) {
        return clientService.findById(id); // Use the injected instance
    }

    @GetMapping("/resttemplate")
    public List<Client> getAllClientsRestTemplate() {
        String url = "http://localhost:8888/clients"; // Using localhost:8888 for service-client
        return restTemplate.getForObject(url, List.class);
    }

    // Get client by ID (RestTemplate)
    @GetMapping("/resttemplate/{id}")
    public Client getClientByIdRestTemplate(@PathVariable Long id) {
        String url = "http://localhost:8888/clients/" + id; // Using localhost:8888 for service-client
        return restTemplate.getForObject(url, Client.class);
    }

    @GetMapping("/webclient")
    public List<Client> getAllClientsWebClient() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8888/clients") // Using localhost:8888 for service-client
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    // Get client by ID (WebClient)
    @GetMapping("/webclient/{id}")
    public Client getClientByIdWebClient(@PathVariable Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8888/clients/" + id) // Using localhost:8888 for service-client
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }
}
