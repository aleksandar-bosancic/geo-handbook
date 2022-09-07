package com.rbhp.geohandbook.ui.cities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.data.SingleLiveEvent;
import com.rbhp.geohandbook.data.WeatherData;
import com.rbhp.geohandbook.http.APIInterface;
import com.rbhp.geohandbook.http.RetrofitHttp;
import com.rbhp.geohandbook.util.FileUtil;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitiesViewModel extends AndroidViewModel implements CityListener {
    private static final String WEATHER_IMAGE_URL = "https://openweathermap.org/img/w/";
    private static final String PNG_EXTENSION_STRING = ".png";
    private static final String NUMBER_OF_IMAGES = "number_of_images";
    private static final String TAG_CACHE_LOAD = "Picasso";

    private final MutableLiveData<List<CityData>> cityListMutableLiveData;
    private final SingleLiveEvent<CityData> clickedCityMap;
    private final SingleLiveEvent<WeatherData> clickedCityWeather;
    private final SingleLiveEvent<CityData> clickedCityImage;
    private final SingleLiveEvent<CityData> clickedCityVideo;
    private Integer numberOfImages;


    public CitiesViewModel(Application application) {
        super(application);
        FileUtil fileUtil = new FileUtil();
        clickedCityMap = new SingleLiveEvent<>();
        cityListMutableLiveData = new MutableLiveData<>();
        cityListMutableLiveData.setValue(fileUtil.loadData(getApplication().getApplicationContext(),
                new TypeToken<List<CityData>>() {
                }.getType()));
        clickedCityWeather = new SingleLiveEvent<>();
        clickedCityImage = new SingleLiveEvent<>();
        clickedCityVideo = new SingleLiveEvent<>();
        loadNumberOfImages();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, CityData cityData) {
        Picasso.get()
                .load(cityData.getImageUrls().get(0))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG_CACHE_LOAD, "Cities onSuccess: Loaded from cache");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(cityData.getImageUrls().get(0))
                                .into(imageView);
                    }
                });
    }

    @BindingAdapter({"weatherUrl"})
    public static void loadWeatherIcon(ImageView imageView, WeatherData weatherData) {
        if (weatherData == null) {
            return;
        }
        Picasso.get()
                .load(WEATHER_IMAGE_URL + weatherData.getWeatherDescription().get(0).icon + PNG_EXTENSION_STRING)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .resize(160, 160)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG_CACHE_LOAD, "Weather onSuccess: Loaded from cache");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(WEATHER_IMAGE_URL + weatherData.getWeatherDescription().get(0).icon + PNG_EXTENSION_STRING)
                                .resize(160, 160)
                                .into(imageView);
                    }
                });
    }

    @BindingAdapter({"cityImageUrl"})
    public static void loadCityImage(ImageView imageView, String url) {
        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG_CACHE_LOAD, "City images onSuccess: Loaded from cache");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(url)
                                .into(imageView);
                    }
                });
    }

    @Override
    public void onCityClick(CityData city) {
        clickedCityMap.setValue(city);
    }

    public void onWeatherClick(CityData cityData) {
        APIInterface apiInterface = RetrofitHttp.getRetrofit().create(APIInterface.class);

        apiInterface.getWeather(cityData.getCoordinates().latitude, cityData.getCoordinates().longitude)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                        if (response.body() != null) {
                            setClickedCityWeather(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                        Log.e("NETWORK", "Could not fetch weather data");
                        Toast.makeText(getApplication().getApplicationContext(),
                                "Could not fetch weather data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void imageOnClick(CityData cityData) {
        clickedCityImage.postValue(cityData);
    }

    public void videoOnClick(CityData cityData) {
        clickedCityVideo.postValue(cityData);
    }

    public void loadNumberOfImages() {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        setNumberOfImages(preferences.getInt(NUMBER_OF_IMAGES, 5));
    }

    public SingleLiveEvent<CityData> getClickedCityMap() {
        return clickedCityMap;
    }

    public void setClickedCityMap(CityData cityData) {
        clickedCityMap.setValue(cityData);
    }

    public MutableLiveData<WeatherData> getClickedCityWeather() {
        return clickedCityWeather;
    }

    public void setClickedCityWeather(WeatherData weatherData) {
        clickedCityWeather.setValue(weatherData);
    }

    public SingleLiveEvent<CityData> getClickedCityImage() {
        return clickedCityImage;
    }

    public void setClickedCityImage(CityData cityData) {
        clickedCityImage.setValue(cityData);
    }


    public MutableLiveData<List<CityData>> getCityLiveData() {
        return cityListMutableLiveData;
    }

    public void setCityListMutableLiveData(List<CityData> cityList) {
        cityListMutableLiveData.setValue(cityList);
    }

    public Integer getNumberOfImages() {
        return numberOfImages;
    }

    public void setNumberOfImages(Integer numberOfImages) {
        this.numberOfImages = numberOfImages;
    }

    public SingleLiveEvent<CityData> getClickedCityVideo() {
        return clickedCityVideo;
    }
}