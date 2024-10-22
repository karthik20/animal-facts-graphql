package com.karthik.poc.animalfacts.config.interceptors;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.karthik.poc.animalfacts.service.RandomTokenService;

@Component
public class RandomTokenHeaderInterceptor implements ClientHttpRequestInterceptor {

    private RandomTokenService randomTokenService;
    
    public RandomTokenHeaderInterceptor(RandomTokenService randomTokenService) {
        this.randomTokenService = randomTokenService;
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        request.getHeaders().set("X-TOKEN", randomTokenService.getToken());
        var response = execution.execute(request, body);
        logRequestResponseDetails(request, response);

        return response;
    }

    private void logRequestResponseDetails(HttpRequest request, ClientHttpResponse response) throws IOException {
        System.out.println("Request Headers: " + request.getHeaders());
        System.out.println("Response status code: " + response.getStatusCode());
    }
    

    
}
