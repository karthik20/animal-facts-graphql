package com.karthik.poc.animalfacts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.karthik.poc.animalfacts.config.AnimalFactsProperties;
import com.karthik.poc.animalfacts.config.interceptors.RandomTokenHeaderInterceptor;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(AnimalFactsProperties.class)
public class AnimalFactsApplication {

	private final RandomTokenHeaderInterceptor randomTokenHeaderInterceptor;

	@Value("${animalfacts.base-url}")
	private String animalFactsBaseUrl;

	public AnimalFactsApplication(RandomTokenHeaderInterceptor randomTokenHeaderInterceptor) {
		this.randomTokenHeaderInterceptor = randomTokenHeaderInterceptor;
	}

	public static void main(String[] args) {
		SpringApplication.run(AnimalFactsApplication.class, args);
	}

	@Bean
	public RestClient animalFactsRestClient(RestTemplateBuilder builder) {
		return RestClient.builder()
				.baseUrl(animalFactsBaseUrl)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.requestInterceptor(randomTokenHeaderInterceptor)
				.build();
	}
}
