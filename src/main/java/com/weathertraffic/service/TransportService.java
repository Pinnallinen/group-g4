package com.weathertraffic.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weathertraffic.model.TransportStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

//Some structure, such as the if loop, was done with the help of ChatGPT when structuring it.
// Service responsible for calling the Digitraffic API and processing data for stations and sensors
@Service
public class TransportService {
    // Base URL of the Digitraffic API
    private static final String DIGITRAFFIC_API_URL = "https://tie.digitraffic.fi/api/tms/v1";
    // Header to identify the user making the request
    private static final String USER_HEADER = "Digitraffic-User";

    // Method to get transport data for a specific city
    public TransportStatus getTransport(String city) {
        // WebClient to make the API call
        final WebClient client = WebClient.builder()
                .defaultHeader(USER_HEADER, "DT/Tester") // Add the user header to the request
                .build();
                
        // Making a GET request to retrieve all stations
        JsonNode stationResponse = client.get().uri(DIGITRAFFIC_API_URL + "/stations")
                .retrieve().bodyToMono(JsonNode.class).block();     // Convert the response into JSON format and Block until the response is available

        // Creating a TransportStatus object to store the city description and sensor data
        TransportStatus status = new TransportStatus();
        status.setDescription(String.format("Traffic data for %s", city));      // Set the description

        // Check if the response contains stations data in the 'features' array
        if(stationResponse != null && stationResponse.has("features")){
            // Loop through the stations
            for(JsonNode station : stationResponse.get("features")){
                // Extract the station name from the 'properties' field
                String stationName = station.get("properties").get("name").asText();

                // Check if the station name contains the city name (case-sensitive)
                if (stationName.toLowerCase().contains(city.toLowerCase())){
                    // Extract the station ID from the 'properties' field
                    String stationId = station.get("properties").get("id").asText();

                    // Make a second request to get sensor data for this specific station
                    JsonNode sensorResponse = client.get().uri(DIGITRAFFIC_API_URL + "/stations" + stationId + "/data")
                        .retrieve().bodyToMono(JsonNode.class).block(); // Convert sensor data into JSON format and Block execution until the response is available

                    if (sensorResponse != null && sensorResponse.has("sensors")) {
                        // Loop through the list of sensors for the station
                        for (JsonNode sensorData: sensorResponse.get("sensors")){
                            // Extract relevant sensor data fields
                            int sensorId = sensorData.get("id").asInt();
                            String name = sensorData.get("name").asText();
                            String shortName = sensorData.get("shortName").asText();
                            String unit = sensorData.get("unit").asText();
                            String direction = sensorData.get("direction").asText();
                            String description = sensorData.get("description").asText();

                            // Add the sensor data to the TransportStatus object
                            status.addSensorData(sensorId, name, shortName, unit, direction, description);
                        }
                    }
                }
            }
        }

        System.out.println(status.getFormattedData());

        return status;  // Return the TransportStatus object with the processed sensor data
        
        //System.out.println(response);

        //// Call the api and return the response body
        //TransportStatus exampleStatus = new TransportStatus();
        //exampleStatus.setDescription(String.format("Example transport status for %s", city));
        //return exampleStatus;

    }
}