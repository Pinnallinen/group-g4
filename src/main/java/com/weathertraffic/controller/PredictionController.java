package com.weathertraffic.controller;

import com.weathertraffic.model.PredictionStatus;
import com.weathertraffic.service.PredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/predictions")
public class PredictionController {
    @Autowired
    private PredictionService predictionService;

    /**
     * Retrieves weather and traffic predictions for a given city and time.
     *
     * @param city the city for which predictions are requested
     * @param time the time for which predictions are requested, defaults to "current" if not provided
     * @return a ResponseEntity containing the prediction status, including weather and traffic details
     * @throws Exception if an error occurs during the prediction retrieval process
     */
    @GetMapping("/{city}")
    public ResponseEntity<PredictionStatus> getPredictions(
            @PathVariable String city,
            @RequestParam(defaultValue = "current") String time) throws Exception {
        if (Objects.equals(time, "current")) {
            return ResponseEntity.ok(predictionService.getCurrentPredictions(city));
        }
        return ResponseEntity.ok(predictionService.getPredictionsAtTime(city, time));
    }
}