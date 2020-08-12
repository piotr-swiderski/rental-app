package com.swiderski.carrental.feignClient;

import com.swiderski.carrental.feignClient.authorization.JwtResponse;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Configuration
public class TokenAccessInterceptor implements RequestInterceptor {

    @Value("${feign.client.tokenUri}")
    private String tokenUri;

    @Value("${feign.client.username}")
    private String username;

    @Value("${feign.client.password}")
    private String password;

    private static final String AUTHORIZATION = "Authorization";


    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION, "Bearer " + getToken());
    }

    @SneakyThrows
    @Bean
    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(tokenUri, entity, String.class);

        JwtResponse jwtResponse = objectMapper.readValue(Objects.requireNonNull(response.getBody()).toString(), JwtResponse.class);
        return jwtResponse.getToken();
    }

}