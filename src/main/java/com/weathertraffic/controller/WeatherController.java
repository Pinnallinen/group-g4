package com.weathertraffic.controller;

import com.weathertraffic.model.LocationModel;
import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.service.FavoriteCityService;
import com.weathertraffic.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private FavoriteCityService favoriteCityService;

    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherStatus> getCurrentWeather(@PathVariable String cityName, @RequestParam(defaultValue = "current") String time) throws Exception {
        if (Objects.equals(time, "current")) {
            return ResponseEntity.ok(weatherService.getCurrentWeather(cityName));
        }
        return ResponseEntity.ok(weatherService.getWeatherAtTime(cityName, time));
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> addFavoriteCity(@RequestBody LocationModel city) throws IOException {
        favoriteCityService.addFavoriteCity(city);
        favoriteCityService.logFavoriteCities(); // Log favorite cities
        return ResponseEntity.ok("City added to favorites");
    }

    @PutMapping("/favorites")
    public ResponseEntity<String> updateFavoriteCity(@RequestBody LocationModel city) throws IOException {
        favoriteCityService.updateFavoriteCity(city);
        favoriteCityService.logFavoriteCities(); // Log favorite cities
        return ResponseEntity.ok("City updated in favorites");
    }

    @DeleteMapping("/favorites/{cityName}")
    public ResponseEntity<String> deleteFavoriteCity(@PathVariable String cityName) throws IOException {
        favoriteCityService.deleteFavoriteCity(cityName);
        favoriteCityService.logFavoriteCities(); // Log favorite cities
        return ResponseEntity.ok("City removed from favorites");
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<LocationModel>> getFavoriteCities() throws IOException {
        return ResponseEntity.ok(favoriteCityService.getFavoriteCities());
    }
}