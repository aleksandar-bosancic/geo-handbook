package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
