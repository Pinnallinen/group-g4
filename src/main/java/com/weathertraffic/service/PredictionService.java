package com.weathertraffic.service;

import com.weathertraffic.model.PredictionStatus;
import com.weathertraffic.model.WeatherStatus;
import com.weathertraffic.model.TransportStatus;
import com.weathertraffic.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.UnsupportedOperationException;
import java.util.List;
import java.util.Optional;

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
        // and make a prediction based on the weather
        boolean predictionIsDelayed = predictWillTrafficBeDelayed(currentWeather);

        // Fetch current traffic data from TransportService
        TransportStatus currentTraffic = transportService.getTransport(city);
        // and check is the traffic currently actually delayed
        boolean actuallyIsDelayed = isTrafficActuallyDelayed(currentTraffic);

        // Build the PredictionStatus object
        PredictionStatus predictionStatus = new PredictionStatus(city, actuallyIsDelayed, predictionIsDelayed);

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
        // TODO: 
        throw new UnsupportedOperationException();
        /*
        // Fetch weather data for the given time from WeatherService
        WeatherStatus weatherAtTime = weatherService.getWeatherAtTime(city, time);

        // Fetch traffic data for the given time from TransportService
        // TODO: no getTransportAtTime exists
        //TransportStatus trafficAtTime = transportService.getTransportAtTime(city, time);

        

        // Build the PredictionStatus object
        PredictionStatus predictionStatus = new PredictionStatus();
        predictionStatus.setCity(city);
        predictionStatus.setTime(time);

        return predictionStatus; */
    }

    private boolean isTrafficActuallyDelayed(TransportStatus transportStatus) {
        List<SensorData> sensors = transportStatus.getSensors();
    
        // Loop through each SensorData object to find one with "KESKINOPEUS_60MIN" in its name
        for (SensorData sensorData : sensors) {
            if (sensorData.getName().contains("KESKINOPEUS_60MIN")) {
                // These sensors are mainly from the high-speed roads (highways) of Finland
                // as such, we can assume that if the speed on the road drops below the out-of-city limit of 80km/h
                // there is traffic jams on the road
                if (sensorData.getValue() < 80) {
                    return true;
                }
                // If we find a match but speed is not below 80, return false immediately
                return false;
            }
        }
    
        // Return false if no matching SensorData is found
        return false;
    }
    

    private boolean predictWillTrafficBeDelayed(WeatherStatus weatherStatus) {
        boolean predictionIsDelayed = false;
        if ( 
            weatherStatus.getWindSpeed() > 10 // over 10m/s
            || weatherStatus.getRainAmount() > 10 // over 10cm/h
            || weatherStatus.getVisibility() < 2 // below 2km
            || weatherStatus.getSnowAmount() > 2 // over 2cm on the ground
            ) 
        {
            predictionIsDelayed = true;
        }

        return predictionIsDelayed;
    }
}
