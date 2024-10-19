package com.weathertraffic.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherStatusTest {

    @Test
    void testWeatherStatusSettersAndGetters() {
        // Arrange
        WeatherStatus status = new WeatherStatus();
        String description = "Sunny day";
        double temperature = 25.5;
        double windSpeed = 5.2;

        // Act
        status.setDescription(description);
        status.setTemperature(temperature);
        status.setWindSpeed(windSpeed);

        // Assert
        assertEquals(description, status.getDescription());
        assertEquals(temperature, status.getTemperature());
        assertEquals(windSpeed, status.getWindSpeed());
    }
}
