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

    private boolean isTrafficActuallyDelayed(TransportStatus transportStatus) {
        List<SensorData> sensors = transportStatus.getSensors();
    
        // Loop through each SensorData object to find one with "VVAPAAS" in its name
        // which means freeflow percentage from the speed limit. Above 90 is flowing traffic and less is more and more
        // traffic jams
        for (SensorData sensorData : sensors) {
            if (sensorData.getName().contains("VVAPAAS")) {
                // 
                if (sensorData.getValue() < 90) {
                    return true;
                }
                // If we find a match but freeflow speed is NOT below traffic-jam levels immediately return not delayed
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
