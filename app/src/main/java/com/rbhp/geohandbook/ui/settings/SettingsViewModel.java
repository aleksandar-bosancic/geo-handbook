package com.rbhp.geohandbook.ui.settings;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.widget.RadioGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.slider.Slider;

import java.util.Locale;

public class SettingsViewModel extends ViewModel {
    private MutableLiveData<Integer> cacheSize;
    private MutableLiveData<Integer> numberOfImages;
    private MutableLiveData<Integer> checkedLanguage;

    public SettingsViewModel() {
        cacheSize = new MutableLiveData<>(100);
        numberOfImages = new MutableLiveData<>(5);
        checkedLanguage = new MutableLiveData<>();
    }

    @BindingAdapter("android:valueAttrChanged")
    public static void onCacheChanged(Slider slider, InverseBindingListener attrChange) {
        slider.addOnChangeListener(((slider1, value, fromUser) -> {
            attrChange.onChange();
        }));
    }

    @InverseBindingAdapter(attribute = "android:value")
    public static float onCacheChanged(Slider slider) {
        return slider.getValue();
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

    public void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public MutableLiveData<Integer> getCheckedLanguage() {
        return checkedLanguage;
    }

    public void setCheckedLanguage(MutableLiveData<Integer> checkedLanguage) {
        this.checkedLanguage = checkedLanguage;
    }
}