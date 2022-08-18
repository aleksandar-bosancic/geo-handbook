package com.rbhp.geohandbook.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

import java.util.List;

public class MapsFragment extends Fragment {
    private List<CityData> cities;

    public MapsFragment(List<CityData> cityData) {
        cities = cityData;
    }

    private final OnMapReadyCallback callback = googleMap -> {
        MarkerOptions markerOptions = new MarkerOptions();
        for (CityData city : cities) {
            LatLng latLng = city.getCoordinates();
            googleMap.addMarker(markerOptions.position(latLng).title(city.getName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
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
}