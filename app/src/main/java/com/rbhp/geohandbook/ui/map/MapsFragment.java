package com.rbhp.geohandbook.ui.map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
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
import com.rbhp.geohandbook.data.AttractionData;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.databinding.FragmentMapsBinding;
import com.rbhp.geohandbook.ui.attractions.AttractionsViewModel;
import com.rbhp.geohandbook.ui.cities.CitiesViewModel;

import java.util.List;

public class MapsFragment extends Fragment {
    private List<CityData> cities;
    private List<AttractionData> attractions;
    private CityData clickedCity;
    private AttractionData clickedAttraction;
    private CitiesViewModel citiesViewModel;
    private AttractionsViewModel attractionsViewModel;
    FragmentMapsBinding binding;
    Boolean citiesMap = false;
    Boolean attractionsMap = false;

    private final OnMapReadyCallback cityCallback = googleMap -> {
        MarkerOptions markerOptions = new MarkerOptions();
        for (CityData city : cities) {
            LatLng latLng = city.getCoordinates();
            if (city.equals(clickedCity)) {
                googleMap.addMarker(markerOptions.position(latLng)
                        .title(city.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
            } else {
                googleMap.addMarker(markerOptions.position(latLng)
                        .title(city.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        }
    };

    @SuppressLint("PotentialBehaviorOverride")
    private final OnMapReadyCallback attractionCallback = googleMap -> {
        MarkerOptions markerOptions = new MarkerOptions();
        for (AttractionData attraction : attractions) {
            LatLng latLng = attraction.getCoordinates();
            if (attraction.equals(clickedAttraction)) {
                googleMap.addMarker(markerOptions.position(latLng)
                        .title(attraction.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
                        .setTag(attraction);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
            } else if (attraction.isFavourite()) {
                googleMap.addMarker(markerOptions.position(latLng)
                        .title(attraction.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                        .setTag(attraction);
            } else {
                googleMap.addMarker(markerOptions.position(latLng)
                        .title(attraction.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                        .setTag(attraction);
            }
        }
        googleMap.setOnMarkerClickListener(marker -> {
            AttractionDialog dialog = new AttractionDialog((AttractionData) marker.getTag());
            dialog.show(requireActivity().getSupportFragmentManager(), "Attraction");
//            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
//            builder.setMessage("AAAAAAAAAAAAAA")
//                    .create()
//                    .show();
            return false;
        });
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        citiesViewModel = new ViewModelProvider(requireActivity()).get(CitiesViewModel.class);
        attractionsViewModel = new ViewModelProvider(requireActivity()).get(AttractionsViewModel.class);

        if (getArguments() != null) {
            citiesMap = getArguments().getBoolean("City");
            attractionsMap = getArguments().getBoolean("Attraction");
        }

        cities = citiesViewModel.getCityLiveData().getValue();
        clickedCity = citiesViewModel.getClickedCityMap().getValue();
        citiesViewModel.setClickedCityMap(null);

        attractions = attractionsViewModel.getAttractionLiveData().getValue();
        clickedAttraction = attractionsViewModel.getClickedAttractionMap().getValue();

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
        if (mapFragment == null) {
            onDestroy();
            return;
        }
        if (Boolean.TRUE.equals(citiesMap)) {
            mapFragment.getMapAsync(cityCallback);
        }
        if (Boolean.TRUE.equals(attractionsMap)) {
            mapFragment.getMapAsync(attractionCallback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}