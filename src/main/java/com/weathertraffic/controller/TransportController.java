package com.weathertraffic.controller;

import com.weathertraffic.model.TransportStatus;
import com.weathertraffic.service.TransportService;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    private TransportService transportService;

    @GetMapping("/status/{city}")
    public ResponseEntity<TransportStatus> getStatus(@PathVariable String city) {
        return ResponseEntity.ok(transportService.getTransport(city));
    }
}