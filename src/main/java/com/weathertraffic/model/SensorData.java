package com.weathertraffic.model;

public class SensorData {

    private int sensorId;
    private String name;
    private String shortName;
    private String unit;
    private double value;              // The measured value from the sensor
    private String timeWindowStart;    // The start time of the measurement window
    private String timeWindowEnd;      // The end time of the measurement window
    private String measuredTime;       // The exact time the measurement was made

    public SensorData(int sensorId, String name, String shortName, String unit,
                      double value, String timeWindowStart, String timeWindowEnd, String measuredTime) {
        this.sensorId = sensorId;
        this.name = name;
        this.shortName = shortName;
        this.unit = unit;
        this.value = value;
        this.timeWindowStart = timeWindowStart;
        this.timeWindowEnd = timeWindowEnd;
        this.measuredTime = measuredTime;
    }

    public SensorData() {
        
    }

    // Getters and setters for all fields

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getName() {
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTimeWindowStart() {
        return timeWindowStart;
    }

    public void setTimeWindowStart(String timeWindowStart) {
        this.timeWindowStart = timeWindowStart;
    }

    public String getTimeWindowEnd() {
        return timeWindowEnd;
    }

    public void setTimeWindowEnd(String timeWindowEnd) {
        this.timeWindowEnd = timeWindowEnd;
    }

    public String getMeasuredTime() {
        return measuredTime;
    }

    public void setMeasuredTime(String measuredTime) {
        this.measuredTime = measuredTime;
    }
}