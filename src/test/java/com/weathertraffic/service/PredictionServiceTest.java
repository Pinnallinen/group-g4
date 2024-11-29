package com.weathertraffic.service;

import com.weathertraffic.model.PredictionStatus;
import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.model.TransportStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PredictionServiceTest {

    @InjectMocks
    private PredictionService predictionService;

    @Mock
    private WeatherService weatherService;

    @Mock
    private TransportService transportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentPredictions_weatherIndicatesDelay_trafficDoesNot() throws Exception {
        String city = "Helsinki";

        // Mock WeatherService
        WeatherStatus weatherStatus = new WeatherStatus();
        weatherStatus.setWindSpeed(15); // Severe wind
        weatherStatus.setRainAmount(0);
        weatherStatus.setVisibility(10);
        weatherStatus.setSnowAmount(0);
        when(weatherService.getCurrentWeather(city)).thenReturn(weatherStatus);

        TransportStatus transportStatus = new TransportStatus();
        transportStatus.setFreeFlowPercentage(95);
        when(transportService.getTransport(city)).thenReturn(transportStatus);

        // Call the method
        PredictionStatus predictionStatus = predictionService.getCurrentPredictions(city);

        // Assert predictions
        assertTrue(predictionStatus.getPrediction());
        assertFalse(predictionStatus.getActualStatus());
        assertEquals(city, predictionStatus.getCity());
    }

    @Test
    void testGetCurrentPredictions_trafficIndicatesDelay_weatherDoesNot() throws Exception {
        String city = "Helsinki";

        // Mock WeatherService
        WeatherStatus weatherStatus = new WeatherStatus();
        weatherStatus.setWindSpeed(1); // Calm weather
        weatherStatus.setRainAmount(0);
        weatherStatus.setVisibility(10);
        weatherStatus.setSnowAmount(0);
        when(weatherService.getCurrentWeather(city)).thenReturn(weatherStatus);

        TransportStatus transportStatus = new TransportStatus();
        transportStatus.setFreeFlowPercentage(85);
        when(transportService.getTransport(city)).thenReturn(transportStatus);

        // Call the method
        PredictionStatus predictionStatus = predictionService.getCurrentPredictions(city);

        // Assert predictions
        assertFalse(predictionStatus.getPrediction());
        assertTrue(predictionStatus.getActualStatus());
        assertEquals(city, predictionStatus.getCity());
    }

    @Test
    void testGetCurrentPredictions_neitherWeatherNorTrafficIndicateDelay() throws Exception {
        String city = "Helsinki";

        // Mock WeatherService
        WeatherStatus weatherStatus = new WeatherStatus();
        weatherStatus.setWindSpeed(1);
        weatherStatus.setRainAmount(0);
        weatherStatus.setVisibility(10);
        weatherStatus.setSnowAmount(0); // Calm weather
        when(weatherService.getCurrentWeather(city)).thenReturn(weatherStatus);

        TransportStatus transportStatus = new TransportStatus();
        transportStatus.setFreeFlowPercentage(95);
        when(transportService.getTransport(city)).thenReturn(transportStatus);

        // Call the method
        PredictionStatus predictionStatus = predictionService.getCurrentPredictions(city);

        // Assert predictions
        assertFalse(predictionStatus.getPrediction());
        assertFalse(predictionStatus.getActualStatus());
        assertEquals(city, predictionStatus.getCity());
    }
}
