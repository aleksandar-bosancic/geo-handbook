package com.rbhp.geohandbook.ui.weather;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rbhp.geohandbook.data.WeatherData;
import com.rbhp.geohandbook.databinding.DialogWeatherBinding;
import com.rbhp.geohandbook.ui.cities.CitiesViewModel;

public class WeatherDialog extends DialogFragment {
    private WeatherData weatherData;
    private final CitiesViewModel viewModel;

    public WeatherDialog(WeatherData weatherData, CitiesViewModel viewModel) {
        this.weatherData = weatherData;
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogWeatherBinding binding = DialogWeatherBinding.inflate(inflater, container, false);
        binding.setWeatherData(weatherData);
        binding.setViewModel(viewModel);
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

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }
}