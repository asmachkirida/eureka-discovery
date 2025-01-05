package com.example.servicevoiture;

import com.example.servicevoiture.model.Voiture;
import com.example.servicevoiture.repository.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class VoitureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoitureServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeH2Database(VoitureRepository voitureRepository) {
        return args -> {
            voitureRepository.save(new Voiture(null, "Toyota", "A 25 333", "Corolla", 1L, null));
            voitureRepository.save(new Voiture(null, "Renault", "B 6 3456", "Megane", 1L, null));
            voitureRepository.save(new Voiture(null, "Peugeot", "A 55 4444", "301", 2L, null));
        };
    }
}