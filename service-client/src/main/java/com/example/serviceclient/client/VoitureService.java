package com.example.serviceclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-voiture")
public interface VoitureService {
    @GetMapping("/voitures/client/{clientId}")
    List<Object> getVoituresByClientId(@PathVariable Long clientId);

    @GetMapping("/voitures")
    List<Object> getAllVoitures();
}