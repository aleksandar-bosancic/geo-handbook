package com.rbhp.geohandbook.ui.images;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.BR;
import com.rbhp.geohandbook.ui.cities.CitiesViewModel;

public class CityImagesRecyclerViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public CityImagesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(String imageUrl, CitiesViewModel viewModel) {
        binding.setVariable(BR.imageUrl, imageUrl);
        binding.setVariable(BR.viewModel, viewModel);
        binding.executePendingBindings();
    }
}
