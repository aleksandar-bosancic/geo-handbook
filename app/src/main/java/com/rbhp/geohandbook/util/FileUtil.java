package com.rbhp.geohandbook.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rbhp.geohandbook.R;

import org.apache.commons.io.IOUtils;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public <T> List<T> loadCityData(Context context, Type typeToken) {
        long time = System.currentTimeMillis();
        List<T> cities = new ArrayList<>();
        String content = "";
        boolean isCityType = TypeToken.get(typeToken).getType().toString().contains("CityData");
        try {
            content = (isCityType) ?
                    IOUtils.toString(context.getResources().openRawResource(R.raw.cities), StandardCharsets.UTF_8) :
                    IOUtils.toString(context.getResources().openRawResource(R.raw.attractions), StandardCharsets.UTF_8);
            IOUtils.close();
            Gson gson = new Gson();
            cities = gson.fromJson(content, typeToken);
        } catch (Exception e) {
            Log.e("Io", "cannot can");
        }
        Log.println(Log.ASSERT, "loading time", String.valueOf((System.currentTimeMillis() - time)));
        return cities;
    }
}
