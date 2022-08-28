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
    private MutableLiveData<Boolean> somethingChanged;

    public SettingsViewModel(Application application) {
        super(application);
        cacheSize = new MutableLiveData<>();
        numberOfImages = new MutableLiveData<>();
        checkedLanguage = new SingleLiveEvent<>();
        somethingChanged = new MutableLiveData<>();
        somethingChanged.setValue(false);
        initialCacheSize();
        initialNumberOfImages();
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
        Integer size = preferences.getInt(CACHE_SIZE, 100);
        setInitialCacheSize(size);
        setCacheSize(size);
    }

    public void initialNumberOfImages() {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Integer size = preferences.getInt(NUMBER_OF_IMAGES, 5);
        setInitialNumberOfImages(size);
        numberOfImages.postValue(size);
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

    public void saveCacheToPreferences(int size) {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CACHE_SIZE, size);
        editor.apply();
    }

    public void saveNumberOfImagesToPreferences(int size) {
        Context context = getApplication().getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(NUMBER_OF_IMAGES, size);
        editor.apply();
    }

    public void apply() {
        if (cacheSize.getValue() != null && numberOfImages.getValue() != null) {
            Integer cache = cacheSize.getValue();
            Integer number = numberOfImages.getValue();
            saveCacheToPreferences(cache);
            saveNumberOfImagesToPreferences(number);
            initialCacheSize = cache;
            initialNumberOfImages = number;
            somethingChanged.setValue(false);
        }
    }

    public void discard() {
        setCacheSize(initialCacheSize);
        setNumberOfImages(initialNumberOfImages);
        somethingChanged.setValue(false);
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

    public void setNumberOfImages(Integer numberOfImages) {
        this.numberOfImages.setValue(numberOfImages);
    }

    public MutableLiveData<Integer> getCheckedLanguage() {
        return checkedLanguage;
    }

    public void setCheckedLanguage(SingleLiveEvent<Integer> checkedLanguage) {
        this.checkedLanguage = checkedLanguage;
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