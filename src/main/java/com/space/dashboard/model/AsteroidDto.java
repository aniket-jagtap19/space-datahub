package com.space.dashboard.model;

public class AsteroidDto {

    private String name;
    private boolean hazardous;

    public AsteroidDto(String name, boolean hazardous) {
        this.name = name;
        this.hazardous = hazardous;
    }

    public String getName() {
        return name;
    }

    public boolean isHazardous() {
        return hazardous;
    }
}