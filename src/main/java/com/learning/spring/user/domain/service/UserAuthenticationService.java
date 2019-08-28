package com.learning.spring.user.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAuthenticationService {

    private final String authenticationEndpoint;

    public UserAuthenticationService(@Value("${auth.validation.endpoint}")String authenticationEndpoint) {
        this.authenticationEndpoint = authenticationEndpoint;
    }

    public String validateTokenAndGetUsername(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(authenticationEndpoint, HttpMethod.GET,
                httpEntity, String.class);

         if (response.getStatusCode().equals(HttpStatus.OK)) {
             return response.getBody();
         }

         throw new RuntimeException("user not valid");
    }
}
