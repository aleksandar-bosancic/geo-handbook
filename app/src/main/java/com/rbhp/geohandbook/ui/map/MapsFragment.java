package com.rbhp.geohandbook.ui.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.databinding.FragmentMapsBinding;
import com.rbhp.geohandbook.ui.cities.CitiesViewModel;

import java.util.List;

public class MapsFragment extends Fragment {
    private List<CityData> cities;
    private CityData clickedCity;
    private CitiesViewModel viewModel;
    FragmentMapsBinding binding;

    //TODO generify
    private final OnMapReadyCallback callback = googleMap -> {
        MarkerOptions markerOptions = new MarkerOptions();
        for (CityData city : cities) {
            LatLng latLng = city.getCoordinates();
            if (city.equals(clickedCity)) {
                googleMap.addMarker(markerOptions.position(latLng).title(city.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
            } else {
                googleMap.addMarker(markerOptions.position(latLng).title(city.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(requireActivity()).get(CitiesViewModel.class);

        cities = viewModel.getCityLiveData().getValue();
        clickedCity = viewModel.getClickedCityMap().getValue();
        viewModel.setClickedCityMap(null);

        if (cities == null || clickedCity == null || cities.isEmpty()) {
            onDestroy();
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_maps);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}