package com.rbhp.geohandbook.ui.images;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.ui.cities.CitiesViewModel;

public class CityImagesRecyclerViewAdapter extends RecyclerView.Adapter<CityImagesRecyclerViewHolder> {
    CitiesViewModel viewModel;
    CityData cityData;

    public CityImagesRecyclerViewAdapter(CityData cityData) {
        this.cityData = cityData;
    }

    @NonNull
    @Override
    public CityImagesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_city_image,
                parent,
                false);
        return new CityImagesRecyclerViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CityImagesRecyclerViewHolder holder, int position) {
        String url = cityData.getImageUrls().get(position);
        holder.bind(url, viewModel);
    }

    @Override
    public int getItemCount() {
        if (cityData == null) {
            return 0;
        }
        return cityData.getImageUrls().size();
    }
}