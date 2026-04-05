package com.space.dashboard.controller;

import com.space.dashboard.model.ApodResponse;
import com.space.dashboard.model.MarsResponse;
import com.space.dashboard.model.AsteroidDto;
import com.space.dashboard.service.NasaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class NasaController {

    private final NasaService nasaService;

    public NasaController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    @GetMapping("/api/apod")
    public ApodResponse getApod() {
        return nasaService.getApod();
    }

    @GetMapping("/api/mars")
    public MarsResponse getMarsPhotos(@RequestParam String earth_date) {
        return nasaService.getMarsPhotos(earth_date);
    }

    @GetMapping("/api/asteroids")
    public List<AsteroidDto> getAsteroids(
            @RequestParam String start,
            @RequestParam String end
    ) {
        return nasaService.getAsteroids(start, end);
    }
}