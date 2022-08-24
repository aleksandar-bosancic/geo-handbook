package com.rbhp.geohandbook.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.rbhp.geohandbook.R;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private FileUtil() {
    }

    public static <T> List<T> loadCityData(Context context, Type typeToken) {
        long time = System.currentTimeMillis();
        List<T> cities = new ArrayList<>();
        String content = "";
        try {
            content = IOUtils.toString(context.getResources().openRawResource(R.raw.cities), StandardCharsets.UTF_8);
            IOUtils.close();
            Gson gson = new Gson();
            cities = gson.fromJson(content, typeToken);
        } catch (IOException e) {
            Log.e("Io", "cannot can");
        }
        Log.println(Log.ASSERT, "loading time", String.valueOf((System.currentTimeMillis() - time)));
        return cities;
    }
}
