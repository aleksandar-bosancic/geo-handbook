package com.rbhp.geohandbook.ui.info;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;

import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.data.CountryData;
import com.rbhp.geohandbook.util.FileUtil;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class InfoViewModel extends AndroidViewModel {
    private static final String TAG_CACHE_LOAD = "Picasso";

    private final CountryData countryData;

    public InfoViewModel(Application application) {
        super(application);
        FileUtil fileUtil = new FileUtil();
        countryData = fileUtil.loadCountryData(getApplication().getApplicationContext());
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG_CACHE_LOAD, "Cities onSuccess: Loaded from cache");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(url)
                                .into(imageView);
                    }
                });
    }

    public CountryData getCountryData() {
        return countryData;
    }
}