package com.rbhp.geohandbook.ui.settings;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.slider.Slider;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.SingleLiveEvent;

public class SettingsViewModel extends AndroidViewModel {
    private static final String SELECTED_LANGUAGE = "selected_language";
    public static final String CACHE_SIZE = "cache_size";
    public static final String NUMBER_OF_IMAGES = "number_of_images";

    private final MutableLiveData<Integer> cacheSize;
    private MutableLiveData<Integer> numberOfImages;
    private SingleLiveEvent<Integer> checkedLanguage;
    private Integer initialLanguage;
    private Integer initialCacheSize;
    private Integer initialNumberOfImages;
    private SingleLiveEvent<Boolean> applyClicked;
    private SingleLiveEvent<Boolean> discardClicked;
    private MutableLiveData<Boolean> somethingChanged;

    public SettingsViewModel(Application application) {
        super(application);
        cacheSize = new MutableLiveData<>(100);
        numberOfImages = new MutableLiveData<>(5);
        checkedLanguage = new SingleLiveEvent<>();
        applyClicked = new SingleLiveEvent<>();
        applyClicked.setValue(false);
        discardClicked = new SingleLiveEvent<>();
        discardClicked.setValue(false);
        somethingChanged = new MutableLiveData<>();
        somethingChanged.setValue(false);
    }

    @BindingAdapter("android:valueAttrChanged")
    public static void onCacheChanged(Slider slider, InverseBindingListener attrChange) {
        slider.addOnChangeListener(((slider1, value, fromUser) -> attrChange.onChange()));
    }

    @InverseBindingAdapter(attribute = "android:value")
    public static float onCacheChanged(Slider slider) {
        return slider.getValue();
    }

    public void initialSelectedLanguage() {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        String language = preferences.getString(SELECTED_LANGUAGE, "en");
        Integer id = (language.equals("sr")) ? R.id.sr : R.id.en;
        setInitialLanguage(id);
        SingleLiveEvent<Integer> singleLiveEventId = new SingleLiveEvent<>();
        singleLiveEventId.setValue(id);
        setCheckedLanguage(singleLiveEventId);
    }

    public void initialCacheSize() {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Integer size = preferences.getInt(CACHE_SIZE, 5);
        setInitialCacheSize(size);
    }

    public void initialNumberOfImages() {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Integer size = preferences.getInt(CACHE_SIZE, 5);
        setInitialNumberOfImages(size);
    }

    public Integer getInitialLanguage() {
        return initialLanguage;
    }

    public void setInitialLanguage(Integer initialLanguage) {
        this.initialLanguage = initialLanguage;
    }

    public void setLocale(String language) {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    public void setCache(long size) {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(CACHE_SIZE, size);
        editor.apply();
    }

    public MutableLiveData<Integer> getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Integer cacheSize) {
        this.cacheSize.setValue(cacheSize);
    }

    public MutableLiveData<Integer> getNumberOfImages() {
        return numberOfImages;
    }

    public void setNumberOfImages(MutableLiveData<Integer> numberOfImages) {
        this.numberOfImages = numberOfImages;
    }

    public MutableLiveData<Integer> getCheckedLanguage() {
        return checkedLanguage;
    }

    public void setCheckedLanguage(SingleLiveEvent<Integer> checkedLanguage) {
        this.checkedLanguage = checkedLanguage;
    }

    public SingleLiveEvent<Boolean> getApplyClicked() {
        return applyClicked;
    }

    public void setApplyClicked(SingleLiveEvent<Boolean> applyClicked) {
        this.applyClicked = applyClicked;
    }

    public SingleLiveEvent<Boolean> getDiscardClicked() {
        return discardClicked;
    }

    public void setDiscardClicked(SingleLiveEvent<Boolean> discardClicked) {
        this.discardClicked = discardClicked;
    }

    public MutableLiveData<Boolean> getSomethingChanged() {
        return somethingChanged;
    }

    public void setSomethingChanged(MutableLiveData<Boolean> somethingChanged) {
        this.somethingChanged = somethingChanged;
    }

    public Integer getInitialCacheSize() {
        return initialCacheSize;
    }

    public void setInitialCacheSize(Integer initialCacheSize) {
        this.initialCacheSize = initialCacheSize;
    }

    public Integer getInitialNumberOfImages() {
        return initialNumberOfImages;
    }

    public void setInitialNumberOfImages(Integer initialNumberOfImages) {
        this.initialNumberOfImages = initialNumberOfImages;
    }
}