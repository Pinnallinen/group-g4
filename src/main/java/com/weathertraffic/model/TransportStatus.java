package com.weathertraffic.model;

import java.util.ArrayList;
import java.util.List;


//Some structure, was done with the help of ChatGPT when structuring it.
// Class representing the transport status in a city, including a list of sensors
public class TransportStatus {
    private String description;             // General description of the transport status
    private List<SensorData> sensors;       // List of all sensors associated with this station
    // Add a bunch of other stuff

    // Constructor initializing the list of sensors
    public TransportStatus(){
        this.sensors = new ArrayList<>();
    }

    // Getter and setter for the transport status description
    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    // Method to add a sensor to the list of sensors
    // Takes all fields of a sensor and creates a new SensorData object
    public void addSensorData(int sensorId, String name, String shortName, String unit, String direction, String description) {
        // Create a new SensorData object with the given data and add it to the list
        SensorData sensorData = new SensorData(sensorId, name, shortName, unit, direction, description);
        this.sensors.add(sensorData);       // Add the sensor to the list of sensors
    }

    // Getter to retrieve the list of sensors
    public List<SensorData> getSensors(){
        return sensors;
    }

    // Method to format the data in a readable way
    public String getFormattedData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transport Status: ").append(description).append("\n");
        sb.append("Sensors Data:\n");
        for (SensorData sensor : sensors) {
            sb.append("  Sensor ID: ").append(sensor.getSensorId()).append("\n")
              .append("  Name: ").append(sensor.getName()).append("\n")
              .append("  Short Name: ").append(sensor.getShortName()).append("\n")
              .append("  Unit: ").append(sensor.getUnit()).append("\n")
              .append("  Direction: ").append(sensor.getDirection()).append("\n")
              .append("  Description: ").append(sensor.getDescription()).append("\n")
              .append("  -----------------------------\n");
        }
        return sb.toString();
    }
}