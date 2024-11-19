package com.weathertraffic.service;

import com.weathertraffic.model.PredictionStatus;
import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.model.TransportStatus;
import com.weathertraffic.model.SensorData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

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

        // Mock TransportService
        SensorData sensor = new SensorData();
        sensor.setSensorId(1);
        sensor.setName("VVAPAAS_HIGHWAY");
        sensor.setShortName("VVAPAAS");
        sensor.setUnit("%");
        sensor.setValue(95); // No traffic delay
        TransportStatus transportStatus = new TransportStatus();
        transportStatus.setSensors(Collections.singletonList(sensor));
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
        weatherStatus.setWindSpeed(5); // Calm weather
        weatherStatus.setRainAmount(0);
        weatherStatus.setVisibility(10);
        weatherStatus.setSnowAmount(0);
        when(weatherService.getCurrentWeather(city)).thenReturn(weatherStatus);

        // Mock TransportService
        SensorData sensor = new SensorData();
        sensor.setSensorId(1);
        sensor.setName("VVAPAAS_HIGHWAY");
        sensor.setShortName("VVAPAAS");
        sensor.setUnit("%");
        sensor.setValue(85); // Traffic delay
        TransportStatus transportStatus = new TransportStatus();
        transportStatus.setSensors(Collections.singletonList(sensor));
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
        weatherStatus.setWindSpeed(5);
        weatherStatus.setRainAmount(0);
        weatherStatus.setVisibility(10);
        weatherStatus.setSnowAmount(0); // Calm weather
        when(weatherService.getCurrentWeather(city)).thenReturn(weatherStatus);

        // Mock TransportService
        SensorData sensor = new SensorData();
        sensor.setSensorId(1);
        sensor.setName("VVAPAAS_HIGHWAY");
        sensor.setShortName("VVAPAAS");
        sensor.setUnit("%");
        sensor.setValue(95); // No traffic delay
        TransportStatus transportStatus = new TransportStatus();
        transportStatus.setSensors(Collections.singletonList(sensor));
        when(transportService.getTransport(city)).thenReturn(transportStatus);

        // Call the method
        PredictionStatus predictionStatus = predictionService.getCurrentPredictions(city);

        // Assert predictions
        assertFalse(predictionStatus.getPrediction());
        assertFalse(predictionStatus.getActualStatus());
        assertEquals(city, predictionStatus.getCity());
    }
}
