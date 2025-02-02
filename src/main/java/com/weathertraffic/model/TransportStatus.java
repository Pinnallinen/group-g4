package com.weathertraffic.model;

import java.util.ArrayList;
import java.util.List;

public class TransportStatus {

    private String description;
    private String trafficStatus;
    private List<SensorData> sensors;
    private double freeFlowPercentage;

    public TransportStatus() {
        this.sensors = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrafficStatus() {
        return trafficStatus;
    }

    public void setTrafficStatus(String trafficStatus) {
        this.trafficStatus = trafficStatus;
    }

    public List<SensorData> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorData> sensors) {
        this.sensors = sensors;
    }

    public void addSensorData(int sensorId, String name, String shortName, String unit,
                              double value, String timeWindowStart, String timeWindowEnd, String measuredTime) {
        SensorData sensorData = new SensorData(sensorId, name, shortName, unit,
                                               value, timeWindowStart, timeWindowEnd, measuredTime);
        this.sensors.add(sensorData);
    }

    public double getFreeFlowPercentage() { return freeFlowPercentage; }

    public void setFreeFlowPercentage(double freeFlowPercentage) { this.freeFlowPercentage = freeFlowPercentage; }

    // Output in console
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transport Status: ").append(description).append("\n");
        sb.append("Traffic Status: ").append(trafficStatus).append("\n");

        if (sensors == null || sensors.isEmpty()) {
            sb.append("No sensor data available.\n");
        } else {
            sb.append("Sensor Data:\n");
            for (SensorData sensor : sensors) {
                sb.append("Sensor ID: ").append(sensor.getSensorId()).append("\n")
                  .append("  Name: ").append(sensor.getName()).append("\n")
                  .append("  Short Name: ").append(sensor.getShortName()).append("\n")
                  .append("  Unit: ").append(sensor.getUnit()).append("\n")
                  .append("  Value: ").append(sensor.getValue()).append("\n")
                  .append("  Time Window Start: ").append(sensor.getTimeWindowStart()).append("\n")
                  .append("  Time Window End: ").append(sensor.getTimeWindowEnd()).append("\n")
                  .append("  Measured Time: ").append(sensor.getMeasuredTime()).append("\n")
                  .append("----------------------------------------\n");
            }
        }

        return sb.toString();
    }
}