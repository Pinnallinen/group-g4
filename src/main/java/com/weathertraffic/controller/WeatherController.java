package com.weathertraffic.controller;

import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.service.WeatherService;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherStatus> getCurrentWeather(@PathVariable String city, @RequestParam(defaultValue = "current") String time) throws Exception {
        if (Objects.equals(time, "current")) {
            return ResponseEntity.ok(weatherService.getCurrentWeather(city));
        }
        return ResponseEntity.ok(weatherService.getWeatherAtTime(city, time));
    }
}