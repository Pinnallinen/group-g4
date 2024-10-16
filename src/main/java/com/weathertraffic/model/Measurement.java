package com.weathertraffic.model;

public class Measurement<T> {
    T data;
    String time;

    public Measurement(T data, String time) {
        this.data = data;
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public String getTime() {
        return time;
    }
}