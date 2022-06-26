package com.rbhp.geohandbook.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mEnteredText;

    public NewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        mEnteredText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getEnteredText() {
        return mEnteredText;
    }

    public void setEnteredText(String mEnteredText) {
        this.mEnteredText.setValue(mEnteredText);
    }

    public void setText(String text){
        this.mText.setValue(text);
    }
}