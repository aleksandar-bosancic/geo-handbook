package com.rbhp.geohandbook.ui.attractions;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.BR;

public class AttractionsRecyclerViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public AttractionsRecyclerViewHolder(@NonNull View itemView, ViewDataBinding binding) {
        super(itemView);
        this.binding = binding;
    }

    public void bind(Object attraction, AttractionsViewModel viewModel) {
        binding.setVariable(BR.attraction, attraction);
        binding.setVariable(BR.viewModel, viewModel);
        binding.executePendingBindings();
    }
}
