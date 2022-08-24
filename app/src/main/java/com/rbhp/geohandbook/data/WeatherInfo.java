package com.rbhp.geohandbook.data;


import com.google.gson.annotations.SerializedName;

public class WeatherInfo {
    @SerializedName("temp")
    public double temp;
    @SerializedName("humidity")
    public int humidity;

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                '}';
    }
}
