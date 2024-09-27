package com.weathertraffic.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weathertraffic.model.TransportStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TransportService {
    // API key, urls, etc...
    private static final String DIGITRAFFIC_API_URL = "https://tie.digitraffic.fi/api/tms/v1/sensors";

    public TransportStatus getTransport(String city) {
        final WebClient client = WebClient.builder()
                .defaultHeader("Digitraffic-User", "DT/Tester")
                .build();

        final JsonNode response = client.get().uri("https://tie.digitraffic.fi/api/tms/v1/sensors")
                .retrieve().bodyToMono(JsonNode.class).block();

        System.out.println(response);

        // Call the api and return the response body
        TransportStatus exampleStatus = new TransportStatus();
        exampleStatus.setDescription(String.format("Example transport status for %s", city));
        return exampleStatus;
    }
}