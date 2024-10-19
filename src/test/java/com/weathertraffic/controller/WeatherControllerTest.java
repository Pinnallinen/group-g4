package com.weathertraffic.controller;

import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCurrentWeather_Success() throws Exception {
        // Arrange
        WeatherStatus mockStatus = new WeatherStatus();
        mockStatus.setDescription("Sunny");
        mockStatus.setTemperature(20.0);
        when(weatherService.getCurrentWeather(anyString())).thenReturn(mockStatus);

        // Act
        ResponseEntity<WeatherStatus> response = weatherController.getCurrentWeather("Helsinki", "current");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Sunny", response.getBody().getDescription());
        assertEquals(20.0, response.getBody().getTemperature());
    }

    @Test
    void getWeatherAtTime_Success() throws Exception {
        // Arrange
        WeatherStatus mockStatus = new WeatherStatus();
        mockStatus.setDescription("Rainy");
        mockStatus.setTemperature(15.0);
        when(weatherService.getWeatherAtTime(anyString(), anyString())).thenReturn(mockStatus);

        // Act
        ResponseEntity<WeatherStatus> response = weatherController.getCurrentWeather("Helsinki", "2024-01-01T12:00:00Z");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Rainy", response.getBody().getDescription());
        assertEquals(15.0, response.getBody().getTemperature());
    }
}