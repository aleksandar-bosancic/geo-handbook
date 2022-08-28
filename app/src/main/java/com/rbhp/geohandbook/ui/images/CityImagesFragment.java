package com.rbhp.geohandbook.ui.images;

import android.os.Bundle;
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

public class CityImagesFragment extends Fragment {
    private FragmentCityImagesBinding binding;
    private RecyclerView recyclerView;
    private CityImagesRecyclerViewAdapter cityImagesRecyclerViewAdapter;
    private CitiesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(CitiesViewModel.class);
        CityData cityData = viewModel.getClickedCityImage().getValue();

        viewModel.setClickedCityImage(null);
        binding = FragmentCityImagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setLifecycleOwner(getViewLifecycleOwner());

        recyclerView = root.findViewById(R.id.city_images_recycler_view);
        if (cityData == null){
            //show error message
        } else {
            cityImagesRecyclerViewAdapter = new CityImagesRecyclerViewAdapter(cityData.getImageUrls().subList(0, viewModel.getNumberOfImages()));
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
