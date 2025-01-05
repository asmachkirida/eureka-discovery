package com.example.servicevoiture.client;

import com.example.servicevoiture.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-client")
public interface ClientService {
    @GetMapping("/clients/{id}")
    Client findById(@PathVariable Long id);
}