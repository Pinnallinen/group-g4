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
        double freeFlowPercentage = transportStatus.getFreeFlowPercentage();
    
        if (freeFlowPercentage < 90) {
            return true;
        }
        return false;
    }
    

    private boolean predictWillTrafficBeDelayed(WeatherStatus weatherStatus) {
        boolean predictionIsDelayed = false;

        if ( 
            weatherStatus.getWindSpeed() > 5 // over 5m/s
            || weatherStatus.getRainAmount() > 4 // over 4cm/h
            || weatherStatus.getVisibility() < 2 // below 2km
            || weatherStatus.getSnowAmount() > 1 // over 1cm on the ground
            ) 
        {
            predictionIsDelayed = true;
        }

        return predictionIsDelayed;
    }
}
