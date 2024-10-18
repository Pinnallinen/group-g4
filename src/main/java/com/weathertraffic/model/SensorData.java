package com.weathertraffic.model;


//Some structure, was done with the help of ChatGPT when structuring it.
// Class representing the data of an individual sensor
public class SensorData {
    private int sensorId;       // Unique ID of the sensor
    private String name;        // Full name of the sensor
    private String shortName;   // Short name describing the sensor metric
    private String unit;        // Unit of measurement (e.g., km/h, kpl/h)
    private String direction;   // Traffic direction measured by the sensor
    private String description; // Description of the sensor (in various languages)


    // Constructor initializing all fields of the sensor
    public SensorData(int sensorId, String name, String shortName, String unit, String direction, String description){
        this.sensorId = sensorId;
        this.name = name;
        this.shortName = shortName;
        this.unit = unit;
        this.direction = direction;
        this.description = description;
    }

    // Getters and setters to access and modify sensor fields
    public int getSensorId(){
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}