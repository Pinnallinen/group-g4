package com.weathertraffic.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weathertraffic.model.TransportStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TransportService {

    private static final String DIGITRAFFIC_API_URL = "https://tie.digitraffic.fi/api/tms/v1";
    private static final String USER_HEADER = "Digitraffic-User";

    public TransportStatus getTransport(String city) {
        final WebClient client = WebClient.builder()
                .defaultHeader(USER_HEADER, "DT/Tester")
                .build();

        // Get all stations
        JsonNode stationResponse = client.get().uri(DIGITRAFFIC_API_URL + "/stations")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        // Create a TransportStatus object to hold the results
        TransportStatus status = new TransportStatus();
        status.setDescription(String.format("Traffic data for %s", city));

        if (stationResponse != null && stationResponse.has("features")) {
            for (JsonNode station : stationResponse.get("features")) {
                String stationName = station.get("properties").get("name").asText();
                String stationId = station.get("properties").get("id").asText();

                // Check if the station name contains the city
                if (stationName.contains(city)) {
                    System.out.println("Station found: " + stationName);
                    System.out.println("Station id: " + stationId);

                    // Fetch sensor data using the stationId
                    JsonNode sensorResponse = client.get().uri(DIGITRAFFIC_API_URL + "/stations/" + stationId + "/data")
                            .retrieve()
                            .bodyToMono(JsonNode.class)
                            .block();

                    // Log the full response from the station data endpoint
                    System.out.println("Full sensor response for station ID " + stationId + ": " + sensorResponse.toString());

                    // Now we access the "sensorValues" field
                    if (sensorResponse != null && sensorResponse.has("sensorValues")) {
                        JsonNode sensorValues = sensorResponse.get("sensorValues");

                        for (JsonNode sensorValue : sensorValues) {
                            try {
                                // Safely extract sensor information, checking for nulls
                                int sensorId = sensorValue.has("id") ? sensorValue.get("id").asInt() : 0;
                                String name = sensorValue.has("name") ? sensorValue.get("name").asText() : "Unknown";
                                String shortName = sensorValue.has("shortName") ? sensorValue.get("shortName").asText() : "N/A";
                                String unit = sensorValue.has("unit") ? sensorValue.get("unit").asText() : "N/A";
                                double value = sensorValue.has("value") ? sensorValue.get("value").asDouble() : 0.0;
                                String timeWindowStart = sensorValue.has("timeWindowStart") ? sensorValue.get("timeWindowStart").asText() : "N/A";
                                String timeWindowEnd = sensorValue.has("timeWindowEnd") ? sensorValue.get("timeWindowEnd").asText() : "N/A";
                                String measuredTime = sensorValue.has("measuredTime") ? sensorValue.get("measuredTime").asText() : "N/A";

                                // Log sensor information to confirm it's being added
                                System.out.println("Adding sensor ID: " + sensorId + " Name: " + name);

                                // Add the sensor data to the TransportStatus object
                                status.addSensorData(sensorId, name, shortName, unit, value, timeWindowStart, timeWindowEnd, measuredTime);
                            } catch (Exception e) {
                                // Log any errors during the data extraction
                                System.err.println("Error processing sensor data: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("No sensors found for station: " + stationName);
                    }
                }
            }
        } else {
            System.out.println("No stations found.");
        }

        // Check if sensors were added
        if (status.getSensors().isEmpty()) {
            System.out.println("No sensors added to the TransportStatus object.");
        } else {
            System.out.println("Sensors successfully added.");
        }
        
        System.out.println(status.toString());
        return status; // Return the TransportStatus object with all data from multiple stations
    }
}
