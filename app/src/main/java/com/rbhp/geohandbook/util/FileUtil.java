package com.rbhp.geohandbook.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
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

    private FileUtil() {
    }

    //TODO Generify
    public static List<CityData> loadCityData(Context context) {
        long time = System.currentTimeMillis();
        List<CityData> cities = new ArrayList<>();
        String content = "";
        try {
            Log.println(Log.ASSERT, "aaa", Looper.myLooper().toString());
            content = IOUtils.toString(context.getResources().openRawResource(R.raw.cities), StandardCharsets.UTF_8);
            IOUtils.close();
            Gson gson = new Gson();
            cities = Arrays.asList(gson.fromJson(content, CityData[].class));
        } catch (IOException e) {
            Log.e("Io", "cannot can");
        }
        Log.println(Log.ASSERT, "loading time", String.valueOf((System.currentTimeMillis() - time)));
        return cities;
    }
}
