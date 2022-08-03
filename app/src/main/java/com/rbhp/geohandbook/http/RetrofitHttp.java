package com.rbhp.geohandbook.http;

import com.rbhp.geohandbook.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {
    private static Retrofit retrofit = null;

    private RetrofitHttp(){}

    public static Retrofit getRetrofit() {
        if (retrofit != null) {
            return retrofit;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rss2json.com/v1/api.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
