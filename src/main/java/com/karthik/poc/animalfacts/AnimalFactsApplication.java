package com.karthik.poc.animalfacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.karthik.poc.animalfacts.config.interceptors.RandomTokenHeaderInterceptor;

@SpringBootApplication
public class AnimalFactsApplication {

	private RandomTokenHeaderInterceptor randomTokenHeaderInterceptor;

	public AnimalFactsApplication(RandomTokenHeaderInterceptor randomTokenHeaderInterceptor) {
		this.randomTokenHeaderInterceptor = randomTokenHeaderInterceptor;
	}

	public static void main(String[] args) {
		SpringApplication.run(AnimalFactsApplication.class, args);

	}

	@Bean
    public RestClient animalFaRestClient(RestTemplateBuilder builder) {
        return RestClient.builder()
                .baseUrl("https://cat-fact.herokuapp.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.requestInterceptor(randomTokenHeaderInterceptor)
                .build();
    }

}
