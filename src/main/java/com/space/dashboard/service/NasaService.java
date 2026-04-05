package com.space.dashboard.service;

import com.space.dashboard.model.ApodResponse;
import com.space.dashboard.model.MarsResponse;
import com.space.dashboard.model.AsteroidDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

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

    //(archived)
    public MarsResponse getMarsPhotos(String date) {
        String url = String.format(
                "%s/mars-photos/api/v1/rovers/curiosity/photos?earth_date=%s&api_key=%s",
                baseUrl, date, apiKey
        );

        System.out.println("URL: " + url);

        return restTemplate.getForObject(url, MarsResponse.class);
    }

    public List<AsteroidDto> getAsteroids(String start, String end) {

        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/neo/rest/v1/feed")
                .queryParam("start_date", start)
                .queryParam("end_date", end)
                .queryParam("api_key", apiKey)
                .toUriString();

        System.out.println("ASTEROID URL: " + url);

        List<AsteroidDto> result = new ArrayList<>();

        try {
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode neo = root.get("near_earth_objects");

            if (neo == null) return result;

            neo.fields().forEachRemaining(entry -> {
                JsonNode dateArray = entry.getValue();

                for (JsonNode asteroid : dateArray) {
                    String name = asteroid.get("name").asText();
                    boolean hazardous = asteroid.get("is_potentially_hazardous_asteroid").asBoolean();

                    result.add(new AsteroidDto(name, hazardous));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}