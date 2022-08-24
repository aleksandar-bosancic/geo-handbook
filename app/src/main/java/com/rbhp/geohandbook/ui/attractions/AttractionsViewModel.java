package com.rbhp.geohandbook.ui.attractions;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.rbhp.geohandbook.data.AttractionData;
import com.rbhp.geohandbook.util.FileUtil;

import java.util.List;

public class AttractionsViewModel extends AndroidViewModel {
    private MutableLiveData<List<AttractionData>> attractionLiveData;

    public AttractionsViewModel(Application application){
        super(application);
        attractionLiveData = new MutableLiveData<>();
        attractionLiveData.setValue(FileUtil.loadCityData(getApplication().getApplicationContext(),
                new TypeToken<List<AttractionData>>(){}.getType()));
    }

    public MutableLiveData<List<AttractionData>> getAttractionLiveData() {
        return attractionLiveData;
    }

    public void setAttractionLiveData(MutableLiveData<List<AttractionData>> attractionLiveData) {
        this.attractionLiveData = attractionLiveData;
    }
}