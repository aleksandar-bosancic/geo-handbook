package com.rbhp.geohandbook.ui.cities;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;
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
    private final MutableLiveData<CityData> clickedCity;
    private final MutableLiveData<WeatherData> clickedCityWeather;
    private final MutableLiveData<CityData> clickedCityImage;


    public CitiesViewModel(Application application) {
        super(application);
        clickedCity = new MutableLiveData<>();
        cityListMutableLiveData = new MutableLiveData<>();
        cityListMutableLiveData.setValue(FileUtil.loadCityData(getApplication().getApplicationContext()));
        clickedCityWeather = new MutableLiveData<>();
        clickedCityImage = new MutableLiveData<>();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, CityData cityData) {
        Picasso.get().load(cityData.getImageUrls().get(0))
                .into(imageView);
    }

    @BindingAdapter({"weatherUrl"})
    public static void loadWeatherIcon(ImageView imageView, WeatherData weatherData) {
        Picasso.get().load(WEATHER_IMAGE_URL + weatherData.weatherDescription.get(0).icon + PNG_EXTENSION_STRING)
                .resize(160, 160)
                .into(imageView);
    }

    @BindingAdapter({"cityImageUrl"})
    public static void loadCityImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public void onCityClick(CityData city) {
        clickedCity.setValue(city);
        clickedCity.setValue(null);
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
                        Log.println(Log.ASSERT, "yajeb", "yajeb");
                    }
                });
    }

    public void imageOnClick(CityData cityData) {
        clickedCityImage.setValue(cityData);
//        clickedCityImage.setValue(null);
    }

    public MutableLiveData<CityData> getClickedCity() {
        return clickedCity;
    }

    public MutableLiveData<WeatherData> getClickedCityWeather() {
        return clickedCityWeather;
    }

    public MutableLiveData<CityData> getClickedCityImage() {
        return clickedCityImage;
    }

    public void setClickedCityWeather(WeatherData weatherData) {
        clickedCityWeather.setValue(weatherData);
    }

    public MutableLiveData<List<CityData>> getCityLiveData() {
        return cityListMutableLiveData;
    }

    public void setCityListMutableLiveData(List<CityData> cityList) {
        cityListMutableLiveData.setValue(cityList);
    }

    public void setClickedCityImage(CityData cityData){
        clickedCityImage.setValue(cityData);
    }
}