package com.rbhp.geohandbook.ui.cities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.databinding.FragmentCitiesBinding;
import com.rbhp.geohandbook.ui.map.MapsFragment;

public class CitiesFragment extends Fragment {
    private FragmentCitiesBinding binding;
    private RecyclerView recyclerView;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;
    private CitiesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CitiesViewModel.class);

        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.cities_recycler_view);
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(citiesRecyclerViewAdapter);
        viewModel.getCityLiveData().observe(getViewLifecycleOwner(),
                cityData -> citiesRecyclerViewAdapter.setCities(cityData));

        viewModel.getClickedCity().observe(getViewLifecycleOwner(), this::openCityOnMap);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return root;
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
        Log.println(Log.ASSERT, "fragment", "clicked" + cityData.getName());
        if (getActivity() != null){
            MapsFragment mapsFragment = new MapsFragment(viewModel.getCityLiveData().getValue());
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_cities, mapsFragment)
                    .addToBackStack("Open map")
                    .commit();
        }
    }
}