package com.rbhp.geohandbook.ui.cities;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewHolder> {
    private final CitiesViewModel viewModel;


    public CitiesRecyclerViewAdapter(CitiesViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public CitiesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_city,
                parent,
                false);
        return new CitiesRecyclerViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesRecyclerViewHolder holder, int position) {
        if (viewModel.getCityLiveData().getValue() != null) {
            CityData city = viewModel.getCityLiveData().getValue().get(position);
            holder.bind(city, viewModel);
        }
    }

    @Override
    public int getItemCount() {
        if (viewModel.getCityLiveData().getValue() == null) {
            return 0;
        }
        return viewModel.getCityLiveData().getValue().size();
    }
}
