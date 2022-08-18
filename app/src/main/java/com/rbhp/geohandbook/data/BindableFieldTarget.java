package com.rbhp.geohandbook.data;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class BindableFieldTarget implements Target {
    private final MutableLiveData<Drawable> observableField;
    private final Resources resources;

    public BindableFieldTarget(MutableLiveData<Drawable> observableField, Resources resources) {
        this.observableField = observableField;
        this.resources = resources;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        observableField.setValue(new BitmapDrawable(resources, bitmap));
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        observableField.setValue(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        observableField.setValue(placeHolderDrawable);
    }
}
