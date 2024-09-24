package com.crud.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherService {
    private final RestTemplate restTemplate;
    private final String apiKey;

    public OpenWeatherService(RestTemplate restTemplate, @Value("${openweather.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public String getWeatherForLocation(String location) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", location, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
