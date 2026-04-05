package com.space.dashboard.service;
import com.space.dashboard.model.MarsResponse;
import com.space.dashboard.model.Photo;
import com.space.dashboard.model.ApodResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
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
    public MarsResponse getMarsPhotos(String date) {
        String url = String.format(
                "%s/mars-photos/api/v1/rovers/curiosity/photos?earth_date=%s&api_key=%s",
                baseUrl, date, apiKey
        );

        System.out.println("URL: " + url);

        return restTemplate.getForObject(url, MarsResponse.class);
    }

    public String getAsteroids(String start, String end) {

        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/neo/rest/v1/feed")
                .queryParam("start_date", start)
                .queryParam("end_date", end)
                .queryParam("api_key", apiKey)
                .toUriString();

        System.out.println("ASTEROID URL: " + url);

        return restTemplate.getForObject(url, String.class);
    }
}