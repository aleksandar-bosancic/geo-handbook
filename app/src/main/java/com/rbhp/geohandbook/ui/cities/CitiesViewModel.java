package com.rbhp.geohandbook.ui.cities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitiesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CitiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}