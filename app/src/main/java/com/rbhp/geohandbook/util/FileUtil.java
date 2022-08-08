package com.rbhp.geohandbook.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    private FileUtil(){}

    public static List<CityData> LoadCityData(Context context) {
        List<CityData> cities = new ArrayList<>();
        String content = "";
        try {
            content = IOUtils.toString(context.getResources().openRawResource(R.raw.cities), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            cities = Arrays.asList(gson.fromJson(content, CityData[].class));
            Log.println(Log.ASSERT, "LOAD", "LOADED");
        } catch (IOException e) {
            Log.e("Io", "cannot can");
        }
        return cities;
    }
}
