package com.weathertraffic.controller;

import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.service.WeatherService;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/current/{city}")
    public ResponseEntity<WeatherStatus> getWeather(@PathVariable String city) throws Exception {
        return ResponseEntity.ok(weatherService.getWeather(city));
    }
}