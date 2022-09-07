package com.rbhp.geohandbook.ui.attractions;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.rbhp.geohandbook.data.AttractionData;
import com.rbhp.geohandbook.data.SingleLiveEvent;
import com.rbhp.geohandbook.util.FileUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AttractionsViewModel extends AndroidViewModel {
    private static final String TAG_CACHE_LOAD = "Picasso";

    private MutableLiveData<List<AttractionData>> attractionLiveData;
    private MutableLiveData<List<AttractionData>> favouriteAttractionLiveData;
    private SingleLiveEvent<AttractionData> clickedAttractionMap;
    private SingleLiveEvent<Boolean> favouritesSelected;
    private final FileUtil fileUtil;
    private final SharedPreferences sharedPreferences;

    public AttractionsViewModel(Application application) {
        super(application);
        fileUtil = new FileUtil();
        attractionLiveData = new MutableLiveData<>();
        favouriteAttractionLiveData = new MutableLiveData<>(new ArrayList<>());
        favouritesSelected = new SingleLiveEvent<>();
        favouritesSelected.setValue(false);
        clickedAttractionMap = new SingleLiveEvent<>();
        attractionLiveData.setValue(fileUtil.loadData(getApplication().getApplicationContext(),
                new TypeToken<List<AttractionData>>() {
                }.getType()));
        sharedPreferences = application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
        Set<String> favourites = sharedPreferences.getStringSet("favourites", new HashSet<>());
        if (!favourites.isEmpty() && attractionLiveData.getValue() != null) {
            favouriteAttractionLiveData.setValue(attractionLiveData.getValue()
                    .stream()
                    .filter(attractionData -> favourites.contains(attractionData.getName()))
                    .collect(Collectors.toList()));
            attractionLiveData.getValue().stream().filter(attractionData -> favouriteAttractionLiveData.getValue().contains(attractionData)).forEach(attractionData -> attractionData.setFavourite(true));
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, AttractionData attractionData) {
        if (attractionData == null) {
            return;
        }
        Picasso.get()
                .load(attractionData.getImageUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG_CACHE_LOAD, "Attractions onSuccess: Loaded from cache");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(attractionData.getImageUrl())
                                .into(imageView);
                    }
                });
    }

    public void updateFavourites(AttractionData attractionData) {
        if (attractionData == null) {
            return;
        }
        if ((attractionData.isFavourite())) {
            favouriteAttractionLiveData.getValue().add(attractionData);
        } else {
            favouriteAttractionLiveData.getValue().remove(attractionData);
        }
    }

    public void persistFavourites() {
        if (favouriteAttractionLiveData.getValue() != null) {
            Set<String> favourites = favouriteAttractionLiveData.getValue().stream().map(AttractionData::getName).collect(Collectors.toSet());
            sharedPreferences.edit().putStringSet("favourites", favourites).apply();
        }
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

    public SingleLiveEvent<AttractionData> getClickedAttractionMap() {
        return clickedAttractionMap;
    }

    public void setClickedAttractionMap(AttractionData attractionData) {
        this.clickedAttractionMap.setValue(attractionData);
    }
}