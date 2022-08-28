package com.rbhp.geohandbook.ui.settings;

import android.app.Activity;
import android.content.Context;
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
import com.rbhp.geohandbook.MainActivity;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.SingleLiveEvent;
import com.rbhp.geohandbook.util.LocaleManager;

import java.util.Locale;

public class SettingsViewModel extends ViewModel {
    private MutableLiveData<Integer> cacheSize;
    private MutableLiveData<Integer> numberOfImages;
    private SingleLiveEvent<Integer> checkedLanguage;
    private Integer initialLanguage;

    public SettingsViewModel() {
        cacheSize = new MutableLiveData<>(100);
        numberOfImages = new MutableLiveData<>(5);
        checkedLanguage = new SingleLiveEvent<>();
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

    public MutableLiveData<Integer> getCheckedLanguage() {
        return checkedLanguage;
    }

    public void setCheckedLanguage(SingleLiveEvent<Integer> checkedLanguage) {
        this.checkedLanguage = checkedLanguage;
    }

    public void initialLanguageSelected(Context requireContext) {
        String language = LocaleManager.getPersistedLanguage(requireContext);
        Integer id = (language.equals("sr")) ? R.id.sr : R.id.en;
        setInitialLanguage(id);
        SingleLiveEvent<Integer> singleLiveEventId = new SingleLiveEvent<>();
        singleLiveEventId.setValue(id);
        setCheckedLanguage(singleLiveEventId);
    }

    public Integer getInitialLanguage() {
        return initialLanguage;
    }

    public void setInitialLanguage(Integer initialLanguage) {
        this.initialLanguage = initialLanguage;
    }
}