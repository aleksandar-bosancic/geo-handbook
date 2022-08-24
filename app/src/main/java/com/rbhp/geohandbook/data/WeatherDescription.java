package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

public class WeatherDescription {
    @SerializedName("main")
    public String main;
    @SerializedName("icon")
    public String icon;

    @Override
    public String toString() {
        return "WeatherDescription{" +
                "main='" + main + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
