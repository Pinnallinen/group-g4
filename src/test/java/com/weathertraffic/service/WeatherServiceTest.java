package com.weathertraffic.service;

import com.weathertraffic.model.WeatherStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCurrentWeather_ReturnsWeatherStatus() throws Exception {
        // Arrange
        String mockXmlResponse = """
            <?xml version="1.0" encoding="UTF-8"?>
            <wfs:FeatureCollection xmlns:wfs="http://www.opengis.net/wfs/2.0">
                <wml2:MeasurementTimeseries gml:id="mts-1-1-temperature">
                    <wml2:point>
                        <wml2:MeasurementTVP>
                            <wml2:time>2024-01-01T12:00:00Z</wml2:time>
                            <wml2:value>20.5</wml2:value>
                        </wml2:MeasurementTVP>
                    </wml2:point>
                </wml2:MeasurementTimeseries>
            </wfs:FeatureCollection>
            """;
        
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockXmlResponse);

        // Act
        WeatherStatus result = weatherService.getCurrentWeather("Helsinki");

        // Assert
        assertNotNull(result);
        assertTrue(result.getDescription().contains("Helsinki"));
    }
}
