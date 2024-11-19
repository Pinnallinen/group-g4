package com.weathertraffic.model;

public class PredictionStatus {
    private String city;

    // The actual prediction
    // is delayed, yes or no
    private boolean isDelayedPrediction;
    
    // The actual current traffic status based on our state-of-the art model
    private boolean isDelayed;

    public PredictionStatus() {

    }

    public PredictionStatus(String city, boolean isDelayed, boolean isDelayedPrediction) {
        this.city = city;
        this.isDelayed = isDelayed;
        this.isDelayedPrediction = isDelayedPrediction;
    }

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean getPrediction() {
        return isDelayedPrediction;
    }

    public void setPrediction(boolean isDelayedPrediction) {
        this.isDelayedPrediction = isDelayedPrediction;
    }

    public boolean getActualStatus() {
        return isDelayed;
    }

    public void setActualStatus(boolean isDelayed) {
        this.isDelayed = isDelayed;
    }
}
