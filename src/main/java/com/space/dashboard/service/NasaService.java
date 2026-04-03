package com.space.dashboard.service;

import com.space.dashboard.model.ApodResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NasaService {

    @Value("${nasa.api.key}")
    private String apiKey;

    @Value("${nasa.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ApodResponse getApod() {
        String url = baseUrl + "/planetary/apod?api_key=" + apiKey;
        return restTemplate.getForObject(url, ApodResponse.class);
    }
}