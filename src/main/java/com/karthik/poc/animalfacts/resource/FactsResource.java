package com.karthik.poc.animalfacts.resource;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.karthik.poc.animalfacts.dto.Facts;
import com.karthik.poc.animalfacts.service.FactsService;

@Controller
public class FactsResource {

    private FactsService factsService;

    public FactsResource(FactsService factsService) {
        this.factsService = factsService;
    }

    @QueryMapping
    public List<Facts> facts(@Argument String animal, @Argument int size) {
        return factsService.getRandomFacts(animal, size);
    }
}
