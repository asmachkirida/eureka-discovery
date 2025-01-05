package com.example.serviceclient.client;

import com.example.serviceclient.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-client")
public interface ClientService {
    @GetMapping("/clients")
    List<Client> findAll();

    @GetMapping("/clients/{id}")
    Client findById(@PathVariable Long id);
}