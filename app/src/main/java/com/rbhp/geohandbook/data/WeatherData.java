package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    @SerializedName("weather")
    public List<WeatherDescription> weatherDescription;
    @SerializedName("main")
    public WeatherInfo weatherInfo;
}
