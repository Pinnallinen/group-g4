package com.weathertraffic.model;

public class PredictionStatus {
    private String city;
    private String time;

    // The actual prediction
    // TODO: should this be a boolean (delayed/not delayed) or some other data type?
    private boolean isDelayedPrediction;
    
    // TODO: add the actual current traffic status 
    private boolean isDelayed;

    public PredictionStatus() {

    }
        
    public PredictionStatus(String city, boolean isDelayed) {
        this.city = city;
        this.isDelayed = isDelayed;
    }

    public PredictionStatus(String city, String time, boolean isDelayed) {
        this.city = city;
        this.time = time;
        this.isDelayed = isDelayed;
    }

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getPrediction() {
        return isDelayed;
    }

    public void setPrediction(boolean isDelayed) {
        this.isDelayed = isDelayed;
    }
}
