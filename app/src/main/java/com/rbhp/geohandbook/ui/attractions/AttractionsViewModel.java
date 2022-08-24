package com.rbhp.geohandbook.ui.attractions;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rbhp.geohandbook.data.AttractionData;

import java.util.List;

public class AttractionsViewModel extends AndroidViewModel {
    private MutableLiveData<List<AttractionData>> attractionLiveData;

    public AttractionsViewModel(Application application){
        super(application);
        attractionLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<AttractionData>> getAttractionLiveData() {
        return attractionLiveData;
    }

    public void setAttractionLiveData(MutableLiveData<List<AttractionData>> attractionLiveData) {
        this.attractionLiveData = attractionLiveData;
    }
}