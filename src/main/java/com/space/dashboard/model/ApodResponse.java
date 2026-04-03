package com.space.dashboard.model;

import lombok.Data;

@Data
public class ApodResponse {
    private String date;
    private String explanation;
    private String url;
    private String title;
}