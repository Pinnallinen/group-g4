package com.weathertraffic.service;

import com.weathertraffic.model.PredictionStatus;
import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.model.TransportStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private TransportService transportService;

    /**
     * Fetches current weather and traffic predictions for the specified city.
     *
     * @param city the city for which predictions are requested
     * @return a PredictionStatus object containing current weather and traffic data
     * @throws Exception if an error occurs while fetching data from WeatherService or TransportService
     */
    public PredictionStatus getCurrentPredictions(String city) throws Exception {
        // Fetch current weather data from WeatherService
        WeatherStatus currentWeather = weatherService.getCurrentWeather(city);
        
        // TODO: make the actual prediction based on the weather
        boolean predictionIsDelayed = false;
        if ( 
            currentWeather.getWindSpeed() > 10 
            || currentWeather.getRainAmount() > 10
            || currentWeather.getVisibility() < 2
            || currentWeather.getSnowAmount() > 20
            ) 
        {
            predictionIsDelayed = true;
        }


        // Fetch current traffic data from TransportService
        TransportStatus currentTraffic = transportService.getTransport(city);

        // Build the PredictionStatus object
        PredictionStatus predictionStatus = new PredictionStatus(city, predictionIsDelayed);

        return predictionStatus;
    }

    /**
     * Fetches weather and traffic predictions for a specific city at a given time.
     * 
     * TODO: does it make any sense to make predictions based on a (past) time? probably not
     *
     * @param city the city for which predictions are requested
     * @param time the time for which predictions are requested (e.g., "2024-10-11T14:00:00")
     * @return a PredictionStatus object containing weather and traffic data for the specified time
     * @throws Exception if an error occurs while fetching data from WeatherService or TransportService
     */
    public PredictionStatus getPredictionsAtTime(String city, String time) throws Exception {
        // Fetch weather data for the given time from WeatherService
        WeatherStatus weatherAtTime = weatherService.getWeatherAtTime(city, time);

        // Fetch traffic data for the given time from TransportService
        // TODO: no getTransportAtTime exists
        //TransportStatus trafficAtTime = transportService.getTransportAtTime(city, time);

        // TODO: make the actual prediction based on weather 
        // TODO2: making predictions based on historical data might not make sense?

        // Build the PredictionStatus object
        PredictionStatus predictionStatus = new PredictionStatus();
        predictionStatus.setCity(city);
        predictionStatus.setTime(time);

        return predictionStatus;
    }
}
