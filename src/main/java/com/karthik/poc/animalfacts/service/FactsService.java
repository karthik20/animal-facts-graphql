package com.karthik.poc.animalfacts.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.karthik.poc.animalfacts.dto.Facts;

@Service
public class FactsService {

    private final RestClient restClient;

    public FactsService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Cacheable(value = "animal-facts", key = "#animal + '-' + #size")
    public List<Facts> getRandomFacts(final String animal, final Integer size) {

        var uri = UriComponentsBuilder.fromUriString("/facts/random")
                .queryParam("animal_type", animal)
                .queryParam("amount", size)
                .toUriString();

        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(new ParameterizedTypeReference<List<Facts>>() {});
    }
    
    
}
