package com.weathertraffic.controller;

import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class WeatherControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(WeatherControllerTest.class);

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger.info("Setting up WeatherControllerTest");
    }

    @Test
    @DisplayName("Get Current Weather Returns Success Status")
    void getCurrentWeather_Success() throws Exception {
        logger.info("Starting getCurrentWeather_Success test");
        
        // Arrange
        WeatherStatus mockStatus = new WeatherStatus();
        mockStatus.setDescription("Sunny");
        mockStatus.setTemperature(20.0);
        when(weatherService.getCurrentWeather(anyString())).thenReturn(mockStatus);
        logger.info("Arranged test data: {}", mockStatus.getDescription());

        // Act
        ResponseEntity<WeatherStatus> response = weatherController.getCurrentWeather("Helsinki", "current");
        logger.info("Received response with status code: {}", response.getStatusCode());

        // Assert
        try {
            assertNotNull(response, "Response should not be null");
            assertEquals(200, response.getStatusCode().value(), "Status code should be 200");
            assertEquals("Sunny", response.getBody().getDescription(), "Weather description should match");
            assertEquals(20.0, response.getBody().getTemperature(), "Temperature should match");
            logger.info("All assertions passed successfully");
        } catch (AssertionError e) {
            logger.error("Test failed with assertion error: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    @DisplayName("Get Weather At Specific Time Returns Success Status")
    void getWeatherAtTime_Success() throws Exception {
        logger.info("Starting getWeatherAtTime_Success test");
        
        // Arrange
        WeatherStatus mockStatus = new WeatherStatus();
        mockStatus.setDescription("Rainy");
        mockStatus.setTemperature(15.0);
        when(weatherService.getWeatherAtTime(anyString(), anyString())).thenReturn(mockStatus);
        logger.info("Arranged test data: {}", mockStatus.getDescription());

        // Act
        ResponseEntity<WeatherStatus> response = weatherController.getCurrentWeather("Helsinki", "2024-01-01T12:00:00Z");
        logger.info("Received response with status code: {}", response.getStatusCode());

        // Assert
        try {
            assertNotNull(response, "Response should not be null");
            assertEquals(200, response.getStatusCode().value(), "Status code should be 200");
            assertEquals("Rainy", response.getBody().getDescription(), "Weather description should match");
            assertEquals(15.0, response.getBody().getTemperature(), "Temperature should match");
            logger.info("All assertions passed successfully");
        } catch (AssertionError e) {
            logger.error("Test failed with assertion error: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    @DisplayName("Get Current Weather returns Error Status")
    void getCurrentWeather_Error() throws Exception {
        logger.info("Starting getCurrentWeather_Error test");

        // Arrange
        when(weatherService.getCurrentWeather(anyString())).thenThrow(new RuntimeException("Service unavailable"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            weatherController.getCurrentWeather("Helsinki", "current");
        });
    }
}