package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {
    @SerializedName("weather")
    private List<WeatherDescription> weatherDescription;
    @SerializedName("main")
    private WeatherInfo weatherInfo;

    public List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(List<WeatherDescription> weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
}
