package com.rbhp.geohandbook.ui.attractions;

import android.app.Application;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.rbhp.geohandbook.data.AttractionData;
import com.rbhp.geohandbook.data.SingleLiveEvent;
import com.rbhp.geohandbook.util.FileUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttractionsViewModel extends AndroidViewModel {
    private MutableLiveData<List<AttractionData>> attractionLiveData;
    private MutableLiveData<List<AttractionData>> favouriteAttractionLiveData;
    private SingleLiveEvent<AttractionData> clickedAttractionMap;
    private SingleLiveEvent<Boolean> favouritesSelected;

    public AttractionsViewModel(Application application) {
        super(application);
        attractionLiveData = new MutableLiveData<>();
        favouriteAttractionLiveData = new MutableLiveData<>(new ArrayList<>());
        favouritesSelected = new SingleLiveEvent<>();
        favouritesSelected.setValue(false);
        clickedAttractionMap = new SingleLiveEvent<>();
        attractionLiveData.setValue(FileUtil.loadCityData(getApplication().getApplicationContext(),
                new TypeToken<List<AttractionData>>() {
                }.getType()));
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, AttractionData attractionData) {
        Picasso.get().load(attractionData.getImageUrl())
                .into(imageView);
    }

    public MutableLiveData<List<AttractionData>> getAttractionLiveData() {
        return attractionLiveData;
    }

    public void setAttractionLiveData(MutableLiveData<List<AttractionData>> attractionLiveData) {
        this.attractionLiveData = attractionLiveData;
    }

    public MutableLiveData<List<AttractionData>> getFavouriteAttractionLiveData() {
        return favouriteAttractionLiveData;
    }

    public void setFavouriteAttractionLiveData(MutableLiveData<List<AttractionData>> favouriteAttractionLiveData) {
        this.favouriteAttractionLiveData = favouriteAttractionLiveData;
    }

    public SingleLiveEvent<Boolean> getFavouritesSelected() {
        return favouritesSelected;
    }

    public void setFavouritesSelected(SingleLiveEvent<Boolean> favouritesSelected) {
        this.favouritesSelected = favouritesSelected;
    }

    public void updateFavourites() {
        if (attractionLiveData.getValue() == null) {
            return;
        }
        favouriteAttractionLiveData.setValue(attractionLiveData.getValue().stream().filter(AttractionData::isFavourite).collect(Collectors.toList()));
    }

    public SingleLiveEvent<AttractionData> getClickedAttractionMap() {
        return clickedAttractionMap;
    }

    public void setClickedAttractionMap(AttractionData attractionData) {
        this.clickedAttractionMap.setValue(attractionData);
    }
}