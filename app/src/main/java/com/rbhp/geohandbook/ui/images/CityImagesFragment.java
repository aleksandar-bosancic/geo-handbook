package com.rbhp.geohandbook.ui.images;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.databinding.FragmentCityImagesBinding;
import com.rbhp.geohandbook.ui.cities.CitiesViewModel;

import java.util.Objects;

public class CityImagesFragment extends Fragment {
    private static final String CITY_IMAGES_FRAGMENT_TAG = "CityImagesFragment";

    private FragmentCityImagesBinding binding;
    private RecyclerView recyclerView;
    private CityImagesRecyclerViewAdapter cityImagesRecyclerViewAdapter;
    private CitiesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(CitiesViewModel.class);
        if (getArguments() == null) {
            onDestroy();
        }
        String cityName = getArguments().getString("City");
        CityData cityData = Objects.requireNonNull(viewModel.getCityLiveData().getValue()).stream().filter(city -> city.getName().equals(cityName)).findFirst().orElse(null);

        if (cityData == null) {
            Log.e(CITY_IMAGES_FRAGMENT_TAG, "onCreateView: cityData is null");
            onDestroy();
        }

        viewModel.setClickedCityImage(null);
        binding = FragmentCityImagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setLifecycleOwner(getViewLifecycleOwner());

        recyclerView = root.findViewById(R.id.city_images_recycler_view);
        if (cityData == null) {
            Log.e(CITY_IMAGES_FRAGMENT_TAG, "onCreateView: cityData is null");
        } else {
            int number = viewModel.getNumberOfImages();
            if (cityData.getImageUrls().size() < viewModel.getNumberOfImages()) {
                number = cityData.getImageUrls().size();
            }
            cityImagesRecyclerViewAdapter = new CityImagesRecyclerViewAdapter(cityData.getImageUrls().subList(0, number));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(cityImagesRecyclerViewAdapter);
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
