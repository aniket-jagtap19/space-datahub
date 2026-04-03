package com.space.dashboard.controller;

import com.space.dashboard.model.ApodResponse;
import com.space.dashboard.service.NasaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}