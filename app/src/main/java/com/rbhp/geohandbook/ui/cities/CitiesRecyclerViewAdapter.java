package com.rbhp.geohandbook.ui.cities;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

import java.util.List;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewHolder> {
    private List<CityData> cities;
    private final CityListener cityListener;


    public CitiesRecyclerViewAdapter(CitiesViewModel viewModel, CityListener cityListener) {
        this.cityListener = cityListener;
    }

    @NonNull
    @Override
    public CitiesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_city,
                parent,
                false);
        return new CitiesRecyclerViewHolder(binding.getRoot(), cityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesRecyclerViewHolder holder, int position) {
        CityData city = cities.get(position);
        holder.bind(city);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public List<CityData> getCities() {
        return cities;
    }

    public void setCities(List<CityData> cities) {
        this.cities = cities;
    }
}
