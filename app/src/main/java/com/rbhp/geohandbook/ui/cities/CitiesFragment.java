package com.rbhp.geohandbook.ui.cities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.FragmentCitiesBinding;
import com.rbhp.geohandbook.util.FileUtil;

public class CitiesFragment extends Fragment implements CityListener{

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
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(citiesRecyclerViewAdapter);
        citiesRecyclerViewAdapter.setCities(FileUtil.LoadCityData(getContext()));

//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//        final TextView textView = binding.textHome;
//        citiesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnCityClick(int position) {

    }
}