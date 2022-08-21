package com.rbhp.geohandbook.http;

import com.rbhp.geohandbook.data.NewsFeed;
import com.rbhp.geohandbook.data.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    String NEWS_URL =  "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Ffeeds.feedburner.com%2FNezavisneNovine";
    String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?appid=f9739efac0ca71cd58c89a3f1b96af46&units=metric";

    @GET(NEWS_URL)
    Call<NewsFeed> getNews();

    @GET(WEATHER_URL)
    Call<WeatherData> getWeather(@Query("lat") double lat, @Query("lon") double lng);
}
