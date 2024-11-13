package com.weathertraffic.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathertraffic.model.TransportStatus;

import reactor.core.publisher.Mono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

class TransportServiceTest {
    
    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;
    
    @Mock
    private RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private ResponseSpec responseSpec;

    @InjectMocks
    private TransportService transportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.build()).thenReturn(webClient);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetTransport_ReturnsTransportStatus() {
        // Mock station data
        String stationJson = """
            {"features" : [ {
                "properties" : {
                    "id" : 20002,
                    "name" : "vt1_Espoo_Hirvisuo"
                    }
                }, {
                "properties" : {
                    "id" : 20004,
                    "name" : "vt1_Espoo_Kasavuori"
                    }
                } ]
            }
            """;

        // Mock sensor data
        String sensorJson = """
                {"sensorValues": [ {
                    "name" : "KESKINOPEUS_5MIN_LIUKUVA_SUUNTA1_VVAPAAS1",
                    "value" : 103.0,
                    "unit" : "***"
                    }, {
                    "name" : "KESKINOPEUS_5MIN_LIUKUVA_SUUNTA2_VVAPAAS2",
                    "value" : 104.0,
                    "unit" : "***"
                    } ]
                }
                """;

        try {
            // Parse JSON strings to JsonNode
            JsonNode stationResponse = objectMapper.readTree(stationJson);
            JsonNode sensorResponse = objectMapper.readTree(sensorJson);

            // Web client mock for station data
            doReturn(requestHeadersUriSpec).when(webClient).get();
            doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(contains("/stations"));
            doReturn(responseSpec).when(requestHeadersSpec).retrieve();
            doReturn(Mono.just(stationResponse)).when(responseSpec).bodyToMono(JsonNode.class);

            // WebClient mock for sensor data
            doReturn(requestHeadersSpec).when(requestHeadersUriSpec).uri(contains("/stations/20004/data"));
            doReturn(responseSpec).when(requestHeadersSpec).retrieve();
            doReturn(Mono.just(sensorResponse)).when(responseSpec).bodyToMono(JsonNode.class);

            TransportStatus result = transportService.getTransport("Espoo");

            assertNotNull(result, "TransportStatus should not be null");


        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred during JSON processing: " + e.getMessage());
        }



    }
}
