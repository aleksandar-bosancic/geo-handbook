package com.rbhp.geohandbook.ui.map;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.AttractionData;
import com.rbhp.geohandbook.databinding.DialogAttractionBinding;

public class AttractionDialog extends DialogFragment {
    private AttractionData attractionData;

    public AttractionDialog(AttractionData attractionData) {
        this.attractionData = attractionData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogAttractionBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_attraction, container, false);
        binding.setAttraction(attractionData);
        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }
}
