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

                        double totalValue = 0;
                        double value1 = 0;
                        int count = 0;

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

                                // Check if the sensor name matches the specified criteria
                                if (name.contains("LIUKUVA_SUUNTA1_VVAPAAS1") || name.contains("LIUKUVA_SUUNTA2_VVAPAAS2")) {
                                   value1 = value / 100;
                                   System.out.println("Value is:" + value + " and the new is:" + value1);
                                   totalValue += value1;
                                   count ++; 
                                }
                                System.out.println("Total value is:" + totalValue);
                                // Log sensor information to confirm it's being added
                                System.out.println("Adding sensor ID: " + sensorId + " Name: " + name);

                                // Add the sensor data to the TransportStatus object
                                status.addSensorData(sensorId, name, shortName, unit, value, timeWindowStart, timeWindowEnd, measuredTime);
                            } catch (Exception e) {
                                // Log any errors during the data extraction
                                System.err.println("Error processing sensor data: " + e.getMessage());
                            }
                        }
                        // Calculate the average and determine traffic status
                        if (count > 0) {
                            double averageValue = (totalValue / count) * 100;
                            String trafficStatus = classifyTraffic(averageValue);
                            status.setTrafficStatus(trafficStatus);
                            status.setFreeFlowPercentage(averageValue);
                            System.out.println("Traffic Value is:" + averageValue);

                        } else {
                            status.setTrafficStatus("No relevant sensor data available.");
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


    //function classifyTraffic
    private String classifyTraffic(double averageValue) {
        if (averageValue >= 0 && averageValue < 10) {
            return "Stationary";
        } else if (averageValue >= 10 && averageValue < 25) {
            return "Queuing";
        }else if (averageValue >= 25 && averageValue < 75) {
            return "Slow";
        }else if (averageValue >= 75 && averageValue < 90) {
            return "Platooning";
        }else if (averageValue >= 90 && averageValue < 100) {
            return "Fluent";
        }else {
            return "Over the free flow speed";
        }
    }
}
