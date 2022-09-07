package com.rbhp.geohandbook.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CountryData;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public <T> List<T> loadData(Context context, Type typeToken) {
        long time = System.currentTimeMillis();
        List<T> data = new ArrayList<>();
        String content = "";
        String type = TypeToken.get(typeToken).getType().toString();
        try {
            InputStream inputStream = (type.contains("CityData")) ?
                    context.getResources().openRawResource(R.raw.cities) :
                    context.getResources().openRawResource(R.raw.attractions);
            content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            IOUtils.close(inputStream);
            Gson gson = new Gson();
            data = gson.fromJson(content, typeToken);
        } catch (Exception e) {
            Log.e("Io", "cannot can");
        }
        return data;
    }

    public CountryData loadCountryData(Context context) {
        CountryData data = new CountryData();
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.country);
            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            IOUtils.close();
            Gson gson = new Gson();
            data = gson.fromJson(content, CountryData.class);
        } catch (Exception e) {
            Log.e("Io", "cannot can");
        }
        return data;
    }
}
