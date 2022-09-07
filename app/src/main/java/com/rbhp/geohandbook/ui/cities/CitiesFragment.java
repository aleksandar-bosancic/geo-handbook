package com.rbhp.geohandbook.ui.cities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.data.WeatherData;
import com.rbhp.geohandbook.databinding.FragmentCitiesBinding;
import com.rbhp.geohandbook.ui.weather.WeatherDialog;

public class CitiesFragment extends Fragment {
    private FragmentCitiesBinding binding;
    private RecyclerView recyclerView;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;
    private CitiesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(CitiesViewModel.class);
        viewModel.loadNumberOfImages();

        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.cities_recycler_view);
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(citiesRecyclerViewAdapter);

        viewModel.getClickedCityMap().observe(getViewLifecycleOwner(), this::openCityOnMap);
        viewModel.getClickedCityWeather().observe(getViewLifecycleOwner(), this::openWeatherDialog);
        viewModel.getClickedCityImage().observe(getViewLifecycleOwner(), this::openCityImages);
        viewModel.getClickedCityVideo().observe(getViewLifecycleOwner(), this::openCityVideo);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return root;
    }

    private void openWeatherDialog(WeatherData weatherData) {
        DialogFragment dialog = new WeatherDialog(weatherData, viewModel);
        dialog.show(requireActivity().getSupportFragmentManager(), "Weather");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openCityOnMap(CityData cityData) {
        if (cityData == null) {
            return;
        }
        Bundle arguments = new Bundle();
        arguments.putBoolean("City", true);
        Navigation.findNavController(requireView()).navigate(R.id.cities_navigate_to_maps, arguments);
    }

    private void openCityVideo(CityData cityData) {
        if (cityData == null) {
            return;
        }
        Bundle arg = new Bundle();
        arg.putString("videoUrl", cityData.getVideoUrl());
        Navigation.findNavController(requireView()).navigate(R.id.navigation_video, arg);
    }

    private void openCityImages(CityData cityData) {
        if (cityData == null) {
            return;
        }
        Bundle arguments = new Bundle();
        arguments.putString("City", cityData.getName());
        Navigation.findNavController(requireView()).navigate(R.id.navigate_to_city_images, arguments);
    }
}