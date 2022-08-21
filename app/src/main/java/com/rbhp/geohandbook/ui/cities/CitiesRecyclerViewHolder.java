package com.rbhp.geohandbook.ui.cities;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;


    public CitiesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(Object object, CityListener listener) {
        binding.setVariable(BR.city, object);
        binding.setVariable(BR.listener, listener);
        binding.setVariable(BR.viewModel, listener);
        binding.executePendingBindings();
    }

}
