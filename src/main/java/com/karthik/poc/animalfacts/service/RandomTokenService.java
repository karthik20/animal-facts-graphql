package com.karthik.poc.animalfacts.service;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class RandomTokenService {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789#$%&()@!";
    private static final SecureRandom RANDOM = new SecureRandom();

    public String getToken() {
        return generateRandomString(20);
    }

    private String generateRandomString(int length) {
        return IntStream.range(0, length)
                    .map(i -> RANDOM.nextInt(CHARACTERS.length()))
                    .mapToObj(CHARACTERS::charAt)
                    .map(String::valueOf)
                    .collect(Collectors.joining());

    }
}
