package com.weathertraffic.service;

import com.weathertraffic.model.TransportStatus;
import org.springframework.stereotype.Service;

@Service
public class TransportService {
    // API key, urls, etc...
    
    public TransportStatus getTransport(String city) {
        // Call the api and return the response body
        TransportStatus exampleStatus = new TransportStatus();
        exampleStatus.setDescription(String.format("Example transport status for %s", city));
        return exampleStatus;
    }
}