package com.weathertraffic;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    // API key, urls, etc...
    
    public WeatherStatus getWeather(String city) {
        // Call the api and return the response body
        WeatherStatus exampleStatus = new WeatherStatus();
        exampleStatus.setDescription(String.format("Example weather for %s", city));
        exampleStatus.setTemperature(12.3);
        exampleStatus.setWindSpeed(32.1);
        return exampleStatus;
    }
}
