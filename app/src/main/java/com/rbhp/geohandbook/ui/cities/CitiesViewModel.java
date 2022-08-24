package com.rbhp.geohandbook.ui.cities;

import android.app.Application;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitiesViewModel extends AndroidViewModel implements CityListener {
    private static final String WEATHER_IMAGE_URL = "https://openweathermap.org/img/w/";
    private static final String PNG_EXTENSION_STRING = ".png";
    private final MutableLiveData<List<CityData>> cityListMutableLiveData;
    private final MutableLiveData<CityData> clickedCityMap;
    private final SingleLiveEvent<WeatherData> clickedCityWeather;
    private final MutableLiveData<CityData> clickedCityImage;


    public CitiesViewModel(Application application) {
        super(application);
        clickedCityMap = new MutableLiveData<>();
        cityListMutableLiveData = new MutableLiveData<>();
        cityListMutableLiveData.setValue(FileUtil.loadCityData(getApplication().getApplicationContext(),
                new TypeToken<List<CityData>>(){}.getType()));
        clickedCityWeather = new SingleLiveEvent<>();
        clickedCityImage = new MutableLiveData<>();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, CityData cityData) {
        Picasso.get().load(cityData.getImageUrls().get(0))
                .into(imageView);
    }

    @BindingAdapter({"weatherUrl"})
    public static void loadWeatherIcon(ImageView imageView, WeatherData weatherData) {
        if (weatherData == null) {
            return;
        }
        Picasso.get().load(WEATHER_IMAGE_URL + weatherData.getWeatherDescription().get(0).icon + PNG_EXTENSION_STRING)
                .resize(160, 160)
                .into(imageView);
    }

    @BindingAdapter({"cityImageUrl"})
    public static void loadCityImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
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
                            Log.println(Log.ASSERT, "weather", response.body().toString());
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
        clickedCityImage.setValue(cityData);
    }

    public MutableLiveData<CityData> getClickedCityMap() {
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

    public MutableLiveData<CityData> getClickedCityImage() {
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
}