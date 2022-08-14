package com.rbhp.geohandbook.ui.cities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.PagerTabStrip;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.FragmentCitiesBinding;
import com.rbhp.geohandbook.ui.map.MapsFragment;
import com.rbhp.geohandbook.util.FileUtil;

public class CitiesFragment extends Fragment implements CityListener {
    private FragmentCitiesBinding binding;
    private RecyclerView recyclerView;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;
    private CitiesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Viewmodel for fragment Cities
        viewModel = new ViewModelProvider(this).get(CitiesViewModel.class);
        //Databinding
        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Initializing RecyclerView
        recyclerView = root.findViewById(R.id.cities_recycler_view);
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(viewModel, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(citiesRecyclerViewAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        citiesRecyclerViewAdapter.setCities(FileUtil.loadCityData(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnCityClick(int position) {
        Log.println(Log.ASSERT, "aaaa", "OnCityClick() called with: position = [" + position + "]");
        MapsFragment mapsFragment = new MapsFragment(FileUtil.loadCityData(getContext()));
        Toast.makeText(getContext(), "klik na grad", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_cities, mapsFragment)
                .addToBackStack("Open map")
                .commit();
    }
}