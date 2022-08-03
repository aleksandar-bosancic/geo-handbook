package com.rbhp.geohandbook.http;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityListItemData;
import com.rbhp.geohandbook.data.NewsFeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    String NEWS_URL =  "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Ffeeds.feedburner.com%2FNezavisneNovine";

    @GET(NEWS_URL)
    Call<NewsFeed> getNews();
}
