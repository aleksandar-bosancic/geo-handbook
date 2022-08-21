package com.rbhp.geohandbook.ui.images;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;
import androidx.navigation.Navigation;
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
    private CityData cityData;

//    public CityImagesFragment(CitiesViewModel viewModel, CityData cityData){
//        this.viewModel = viewModel;
//        this.cityData = cityData;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(CitiesViewModel.class);
        Toast.makeText(getContext(), viewModel.getCityLiveData().getValue().get(0).getName(), Toast.LENGTH_SHORT).show();
        cityData = viewModel.getClickedCityImage().getValue();
        viewModel.setClickedCityImage(null);
        binding = FragmentCityImagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                Toast.makeText(requireContext(), Navigation.findNavController(requireView()).getBackQueue().getSize(), Toast.LENGTH_SHORT).show();
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_city_images_to_navigation_cities);
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        binding.setLifecycleOwner(getViewLifecycleOwner());

//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), );

        recyclerView = root.findViewById(R.id.city_images_recycler_view);
        cityImagesRecyclerViewAdapter = new CityImagesRecyclerViewAdapter(cityData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cityImagesRecyclerViewAdapter);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
