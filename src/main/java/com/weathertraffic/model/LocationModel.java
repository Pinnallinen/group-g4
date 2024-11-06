package com.weathertraffic.model;

public class LocationModel {
    private double lat;
    private double lon;
    private String cityName;
    private String country;

    public LocationModel(double lat, double lon, String cityName, String country) {
        this.lat = lat;
        this.lon = lon;
        this.cityName = cityName;
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}