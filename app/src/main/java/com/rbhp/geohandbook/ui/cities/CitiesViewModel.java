package com.rbhp.geohandbook.ui.cities;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.util.FileUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CitiesViewModel extends AndroidViewModel implements CityListener {
    private final MutableLiveData<List<CityData>> cityListMutableLiveData;
    private final MutableLiveData<CityData> clickedCity;
    private Drawable cityImage;
    private MutableLiveData<List<Drawable>> cityImageList;

    public CitiesViewModel(Application application) {
        super(application);
        clickedCity = new MutableLiveData<>();
        cityListMutableLiveData = new MutableLiveData<>();
        cityListMutableLiveData.setValue(FileUtil.loadCityData(getApplication().getApplicationContext()));
        Log.println(Log.ASSERT, "cities", String.valueOf(cityListMutableLiveData.getValue()));
        cityImageList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CityData>> getCityLiveData() {
        return cityListMutableLiveData;
    }

    public void setCityListMutableLiveData(List<CityData> cityList) {
        cityListMutableLiveData.setValue(cityList);
    }

    @Override
    public void onCityClick(CityData city) {
        Log.println(Log.ASSERT, "viewmodel", "clicky    " + city.getName());
        clickedCity.setValue(city);
        clickedCity.setValue(null);
    }

    public MutableLiveData<CityData> getClickedCity() {
        return clickedCity;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, CityData cityData) {
        Picasso.get().load(cityData.getImageUrls().get(0)).into(imageView);
    }

    public Drawable getCityImage() {
        return cityImage;
    }

    public void setCityImage(Drawable cityImage) {
        this.cityImage = cityImage;
    }

//    public void getCityImage(CityData city) {
//        cityImage = new MutableLiveData<>();
//        BindableFieldTarget bindableFieldTarget = new BindableFieldTarget(cityImage, getApplication().getResources());
//        Picasso.get().load(city.getImageUrls().get(0)).into(bindableFieldTarget);
//        cityImageList.getValue().add(cityImage.getValue());
//    }
//
//    public MutableLiveData<List<Drawable>> getCityImageList() {
//        return cityImageList;
//    }
//
//    public void setCityImageList(MutableLiveData<List<Drawable>> cityImageList) {
//        this.cityImageList = cityImageList;
//    }
}